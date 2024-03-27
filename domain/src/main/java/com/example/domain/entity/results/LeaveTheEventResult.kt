package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.LeaveTheEventAsFunDetailDataEntity
import com.example.domain.entity.responses.errors.LeaveTheEventAsPlayerDetailDataEntity
import com.example.domain.entity.responses.success.LeaveTheEventAsFanResponseEntity
import com.example.domain.entity.responses.success.LeaveTheEventAsPlayerResponseEntity

sealed class LeaveTheEventAsFanResult {
    data class Success(val success: LeaveTheEventAsFanResponseEntity) : LeaveTheEventAsFanResult()
    data class Error(val error: LeaveTheEventAsFunDetailDataEntity) : LeaveTheEventAsFanResult()
}

sealed class LeaveTheEventAsPlayerResult {
    data class Success(val success: LeaveTheEventAsPlayerResponseEntity) : LeaveTheEventAsPlayerResult()
    data class Error(val error: LeaveTheEventAsPlayerDetailDataEntity) : LeaveTheEventAsPlayerResult()
}