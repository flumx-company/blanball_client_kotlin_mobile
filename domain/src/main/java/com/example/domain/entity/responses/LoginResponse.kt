package com.example.domain.entity.responses

data class LoginResponse(
    val code: Int,
    val `data`: LoginData,
    val message: String? = null,
    val status: String
)

data class LoginData(
    val email: String,
    val tokens: LoginTokens,
    )

data class ErrorResponse(
    val code: Int,
    val `data`: LoginDataError,
    val message: Any? = null,
    val status: String
)

data class LoginDataError(
    val errors: List<LoginErrorsDomain>,
    val type: String
)

data class LoginErrorsDomain(
    val detail: String
)

data class LoginTokens(
    val access: String,
    val refresh: String
)