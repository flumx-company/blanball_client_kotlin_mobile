package com.example.blanball.utils

import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textfield.TextInputEditText

class Utils {
    companion object {
        fun animateEditTextFocus(editTextArray: Array<TextInputEditText>, container: View) {
            var isFirstFocus = true

            for (i in editTextArray) {
                i.setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus && isFirstFocus) {
                        val anim = ValueAnimator.ofInt(120, 0)
                        anim.duration = 1000
                        anim.addUpdateListener { valueAnimator ->
                            val layoutParams =
                                container.layoutParams as ViewGroup.MarginLayoutParams
                            layoutParams.topMargin = valueAnimator.animatedValue as Int
                            container.layoutParams = layoutParams
                        }
                        anim.start()
                        isFirstFocus = false
                    }
                }
            }
        }
    }
}