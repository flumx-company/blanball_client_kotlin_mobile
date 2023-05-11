package com.example.blanball.utils.di.validation

import me.naingaungluu.formconductor.annotations.EmailAddress
import me.naingaungluu.formconductor.annotations.Form

@Form
data class EmailForm(
    @EmailAddress
    val email: String = ""
)
