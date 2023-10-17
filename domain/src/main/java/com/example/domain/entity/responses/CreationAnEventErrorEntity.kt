package com.example.domain.entity.responses

data class CreationAnEventErrorEntity(
    val code: Int,
    val `data`: CreationAnEventErrorEntityData,
    val message: Any? = null,
    val status: String
)

data class CreationAnEventErrorEntityData(
    val errors: List<CreationAnEventErrorDetailEntity>,
    val type: String
)

data class CreationAnEventErrorDetailEntity(
    val detail: String
)