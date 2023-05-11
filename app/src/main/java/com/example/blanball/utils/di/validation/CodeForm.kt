package com.example.blanball.utils.di.validation

import me.naingaungluu.formconductor.annotations.Form
import me.naingaungluu.formconductor.annotations.MaxLength
import me.naingaungluu.formconductor.annotations.MinLength

@Form
data class CodeForm(
    @MaxLength(68)
    @MinLength(8)
    val codeChar: String
)
