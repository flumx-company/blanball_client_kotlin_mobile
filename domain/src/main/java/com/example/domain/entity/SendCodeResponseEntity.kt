package com.example.domain.entity

data class SendCodeResponseEntity(
    val code: Int,
    val `data`: DataSendCodeDomain,
    val message: Any? = null,
    val status: String
)