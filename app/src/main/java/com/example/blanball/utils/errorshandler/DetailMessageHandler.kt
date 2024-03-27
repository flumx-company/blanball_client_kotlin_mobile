package com.example.blanball.utils.errorshandler

interface DetailMessageHandler {
    suspend fun handleErrorMessage(message: String): String
}