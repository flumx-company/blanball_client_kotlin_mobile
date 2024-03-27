package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.JoinToEventAsFunDetailDataEntity
import com.example.domain.entity.responses.errors.JoinToEventAsPlayerDetailDataEntity
import com.example.domain.entity.responses.success.JoinToEventAsFunResponseEntity
import com.example.domain.entity.responses.success.JoinToEventAsPlayerResponseEntity

sealed class JoinToEventAsFanResult {
    data class Success(val success: JoinToEventAsFunResponseEntity) : JoinToEventAsFanResult()
    data class Error(val error: JoinToEventAsFunDetailDataEntity) : JoinToEventAsFanResult()
}

sealed class JoinToEventAsPlayerResult {
    data class Success(val success: JoinToEventAsPlayerResponseEntity) : JoinToEventAsPlayerResult()
    data class Error(val error: JoinToEventAsPlayerDetailDataEntity) : JoinToEventAsPlayerResult()
}