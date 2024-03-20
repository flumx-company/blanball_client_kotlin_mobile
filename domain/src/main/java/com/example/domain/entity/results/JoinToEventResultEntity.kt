package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.JoinToEventAsFunDetailDataEntity
import com.example.domain.entity.responses.errors.JoinToEventAsPlayerDetailDataEntity
import com.example.domain.entity.responses.success.JoinToEventAsFunResponseEntityData
import com.example.domain.entity.responses.success.JoinToEventAsPlayerResponseEntityData

sealed class JoinToEventAsFanResultEntity {
    data class Success(val data: JoinToEventAsFunResponseEntityData) : JoinToEventAsFanResultEntity()
    data class Error(val error: JoinToEventAsFunDetailDataEntity) : JoinToEventAsFanResultEntity()
}

sealed class JoinToEventAsPlayerResultEntity {
    data class Success(val data: JoinToEventAsPlayerResponseEntityData) : JoinToEventAsPlayerResultEntity()
    data class Error(val error: JoinToEventAsPlayerDetailDataEntity) : JoinToEventAsPlayerResultEntity()
}