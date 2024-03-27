package com.example.blanball.utils.errorshandler

import javax.inject.Inject

class DetailMessageHandlerImpl @Inject constructor() : DetailMessageHandler {
    override suspend fun handleErrorMessage(message: String): String {
        val errorFields = ResponseDetailsMessages::class.members
        for (field in errorFields ) {
            if (field.name == message) {
                return field.call(ResponseDetailsMessages()) as String
            }
        }
        return "Невідома помилка"
    }
}