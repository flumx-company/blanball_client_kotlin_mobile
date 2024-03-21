package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.LeaveTheEventAsFunDetailDataEntity
import com.example.domain.entity.responses.errors.LeaveTheEventAsPlayerDetailDataEntity
import com.example.domain.entity.responses.success.LeaveTheEventAsFanResponseEntityData
import com.example.domain.entity.responses.success.LeaveTheEventAsPlayerResponseEntityData

sealed class LeaveTheEventAsFanResultEntity {
    data class Success(val data: LeaveTheEventAsFanResponseEntityData) : LeaveTheEventAsFanResultEntity()
    data class Error(val error: LeaveTheEventAsFunDetailDataEntity) : LeaveTheEventAsFanResultEntity()
}

sealed class LeaveTheEventAsPlayerResultEntity {
    data class Success(val data: LeaveTheEventAsPlayerResponseEntityData) : LeaveTheEventAsPlayerResultEntity()
    data class Error(val error: LeaveTheEventAsPlayerDetailDataEntity) : LeaveTheEventAsPlayerResultEntity()
}