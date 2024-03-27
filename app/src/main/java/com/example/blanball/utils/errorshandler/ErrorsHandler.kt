package com.example.blanball.utils.errorshandler

interface ErrorsHandler {
    fun handleErrorMessage(errorMessage: String): String
}