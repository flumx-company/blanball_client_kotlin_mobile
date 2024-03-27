package com.example.blanball.utils.errorshandler

import javax.inject.Inject

class ErrorsHandlerImpl @Inject constructor() : ErrorsHandler {
    override fun handleErrorMessage(errorMessage: String): String {
        val errorFields = ErrorsMessages::class.members
        for (field in errorFields ) {
            if (field.name == errorMessage) {
                return field.call(ErrorsMessages()) as String
            }
        }
        return "Неизвестная ошибка"
    }
}