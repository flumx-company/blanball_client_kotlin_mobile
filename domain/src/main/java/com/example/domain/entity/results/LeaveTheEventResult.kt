package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.LeaveTheEventAsFunDetailDataEntity
import com.example.domain.entity.responses.errors.LeaveTheEventAsPlayerDetailDataEntity
import com.example.domain.entity.responses.success.LeaveTheEventAsFanResponseEntityData
import com.example.domain.entity.responses.success.LeaveTheEventAsPlayerResponseEntityData

sealed class LeaveTheEventAsFanResult {
    data class Success(val data: LeaveTheEventAsFanResponseEntityData) : LeaveTheEventAsFanResult()
    data class Error(val error: LeaveTheEventAsFunDetailDataEntity) : LeaveTheEventAsFanResult()
}

sealed class LeaveTheEventAsPlayerResult {
    data class Success(val data: LeaveTheEventAsPlayerResponseEntityData) : LeaveTheEventAsPlayerResult()
    data class Error(val error: LeaveTheEventAsPlayerDetailDataEntity) : LeaveTheEventAsPlayerResult()
}