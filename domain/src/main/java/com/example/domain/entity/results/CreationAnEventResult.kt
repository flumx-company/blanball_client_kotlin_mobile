package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.CreationAnEventErrorDetailEntity
import com.example.domain.entity.responses.success.CreationAnEventResponseEntityData

sealed class CreationAnEventResult {
    data class Success(val data: CreationAnEventResponseEntityData) : CreationAnEventResult()
    data class Error(val error: CreationAnEventErrorDetailEntity) : CreationAnEventResult()
}