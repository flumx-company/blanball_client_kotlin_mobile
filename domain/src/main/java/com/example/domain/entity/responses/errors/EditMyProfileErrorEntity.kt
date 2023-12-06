package com.example.domain.entity.responses.errors

data class EditMyProfileErrorEntity(
    val code: Int,
    val `data`: EditMyProfileErrorEntityData,
    val message: Any,
    val status: String
)

data class EditMyProfileErrorEntityData(
    val errors: List<EditMyProfileErrorEntityDetail>,
    val type: String
)

data class EditMyProfileErrorEntityDetail(
    val detail: String
)