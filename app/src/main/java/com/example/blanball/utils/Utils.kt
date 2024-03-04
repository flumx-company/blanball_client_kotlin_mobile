package com.example.blanball.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.domain.utils.Code.CALL_PHONE_PERMISSION_REQUEST_CODE
import com.example.domain.utils.Endpoints
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.absoluteValue

internal fun makeCall(number: String, context: Context) {
    try {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.CALL_PHONE),
                CALL_PHONE_PERMISSION_REQUEST_CODE

            )
        } else {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$number")
            context.startActivity(intent)
        }
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
}


internal fun writeEmail(
    addresses: Array<String>,
    context: Context,
) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, addresses)
    }
    context.startActivity(intent)
}

internal class MaskVisualTransformation(private val mask: String) : VisualTransformation {

    private val specialSymbolsIndices = mask.indices.filter { mask[it] != '#' }

    override fun filter(text: AnnotatedString): TransformedText {
        var out = ""
        var maskIndex = 0
        text.forEach { char ->
            while (specialSymbolsIndices.contains(maskIndex)) {
                out += mask[maskIndex]
                maskIndex++
            }
            out += char
            maskIndex++
        }
        return TransformedText(AnnotatedString(out), offsetTranslator())
    }

    private fun offsetTranslator() = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val offsetValue = offset.absoluteValue
            if (offsetValue == 0) return 0
            var numberOfHashtags = 0
            val masked = mask.takeWhile {
                if (it == '#') numberOfHashtags++
                numberOfHashtags < offsetValue
            }
            return masked.length + 1
        }

        override fun transformedToOriginal(offset: Int): Int {
            return mask.take(offset.absoluteValue).count { it == '#' }
        }
    }
}

internal val isNavigateToLogin: MutableStateFlow<Boolean> = MutableStateFlow(false)
internal val isNavigateToTechWorks: MutableStateFlow<Boolean> = MutableStateFlow(false)
internal val toPrivacyPolicyUrlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Endpoints.PRIVACY_POLICY_URL))
internal val toFLumXUrlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Endpoints.FLUM_X_URL))

enum class EventTab {
    ALL_EVENTS,
    MY_EVENTS
}