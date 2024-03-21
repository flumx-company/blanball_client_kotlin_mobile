package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.GetRelevantUserSearchListErrorEntityDetail
import com.example.domain.entity.responses.success.GetRelevantUserSearchListResponseEntityData

sealed class GetRelevantUserSearchListResult{
    data class Success(val data: GetRelevantUserSearchListResponseEntityData): GetRelevantUserSearchListResult()
    data class Error(val error: GetRelevantUserSearchListErrorEntityDetail): GetRelevantUserSearchListResult()
}