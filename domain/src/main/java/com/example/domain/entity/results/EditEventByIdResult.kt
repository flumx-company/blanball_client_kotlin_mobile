package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.EditEventByIdResponseErrorDetailEntity
import com.example.domain.entity.responses.success.EditEventByIdResponseEntityData

sealed class EditEventByIdResult {
    data class Success(val data: EditEventByIdResponseEntityData) : EditEventByIdResult()
    data class Error(val error: EditEventByIdResponseErrorDetailEntity) :
        EditEventByIdResult()
}