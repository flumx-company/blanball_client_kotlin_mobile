package com.example.blanball.utils.errorshandler

import android.app.Application
import com.example.blanball.R
import javax.inject.Inject

class DetailMessageHandlerImpl @Inject constructor(
    val application: Application
) : DetailMessageHandler {
    override suspend fun handleErrorMessage(message: String): String {
        val errorFields = ResponseDetailsMessages::class.members
        for (field in errorFields ) {
            if (field.name == message) {
                return field.call(ResponseDetailsMessages()).toString()
            }
        }
        return application.getString(R.string.unknown_error)
    }
}