package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.GetRelevantUserSearchListErrorEntityDetail
import com.example.domain.entity.responses.success.GetRelevantUserSearchListResponseEntityData

sealed class GetRelevantUserSearchListResultEntity{
    data class Success(val data: GetRelevantUserSearchListResponseEntityData): GetRelevantUserSearchListResultEntity()
    data class Error(val error: GetRelevantUserSearchListErrorEntityDetail): GetRelevantUserSearchListResultEntity()
}