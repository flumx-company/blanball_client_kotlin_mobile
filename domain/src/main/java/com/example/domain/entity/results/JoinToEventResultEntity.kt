package com.example.domain.entity.results

import com.example.domain.entity.responses.success.JoinToEventAsFunResponseEntityData
import com.example.domain.entity.responses.success.JoinToEventAsPlayerResponseEntityData
import com.example.domain.entity.responses.errors.JoinToEventAsFunDetailDataEntity
import com.example.domain.entity.responses.errors.JoinToEventAsPlayerDetailDataEntity

sealed class JoinToEventAsFunResultEntity {
    data class Success(val data: JoinToEventAsFunResponseEntityData) : JoinToEventAsFunResultEntity()
    data class Error(val error: JoinToEventAsFunDetailDataEntity) : JoinToEventAsFunResultEntity()
}

sealed class JoinToEventAsPlayerResultEntity {
    data class Success(val data: JoinToEventAsPlayerResponseEntityData) : JoinToEventAsPlayerResultEntity()
    data class Error(val error: JoinToEventAsPlayerDetailDataEntity) : JoinToEventAsPlayerResultEntity()
}