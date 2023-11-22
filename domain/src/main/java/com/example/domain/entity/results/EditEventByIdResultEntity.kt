package com.example.domain.entity.results

import com.example.domain.entity.responses.EditEventByIdResponseEntityData
import com.example.domain.entity.responses.EditEventByIdResponseErrorDetailEntity

sealed class EditEventByIdResultEntity {
    data class Success(val data: EditEventByIdResponseEntityData) : EditEventByIdResultEntity()
    data class Error(val error: EditEventByIdResponseErrorDetailEntity) :
        EditEventByIdResultEntity()
}