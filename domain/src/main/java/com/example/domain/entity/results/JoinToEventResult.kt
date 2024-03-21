package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.JoinToEventAsFunDetailDataEntity
import com.example.domain.entity.responses.errors.JoinToEventAsPlayerDetailDataEntity
import com.example.domain.entity.responses.success.JoinToEventAsFunResponseEntityData
import com.example.domain.entity.responses.success.JoinToEventAsPlayerResponseEntityData

sealed class JoinToEventAsFanResult {
    data class Success(val data: JoinToEventAsFunResponseEntityData) : JoinToEventAsFanResult()
    data class Error(val error: JoinToEventAsFunDetailDataEntity) : JoinToEventAsFanResult()
}

sealed class JoinToEventAsPlayerResult {
    data class Success(val data: JoinToEventAsPlayerResponseEntityData) : JoinToEventAsPlayerResult()
    data class Error(val error: JoinToEventAsPlayerDetailDataEntity) : JoinToEventAsPlayerResult()
}