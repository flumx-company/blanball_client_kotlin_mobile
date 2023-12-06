package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.CreationAnEventErrorDetailEntity
import com.example.domain.entity.responses.CreationAnEventResponseEntityData

sealed class CreationAnEventResultEntity {
    data class Success(val data: CreationAnEventResponseEntityData) : CreationAnEventResultEntity()
    data class Error(val error: CreationAnEventErrorDetailEntity) : CreationAnEventResultEntity()
}