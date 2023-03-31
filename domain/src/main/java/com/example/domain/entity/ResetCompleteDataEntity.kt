package com.example.domain.entity

data class ResetCompleteDataEntity(
    val errors: List<ResetCompleteErrorsEntity>,
    val type: String
)