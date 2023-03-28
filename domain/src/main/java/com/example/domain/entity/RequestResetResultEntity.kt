package com.example.domain.entity

data class RequestResetResultEntity {
    data class Success(val data: ) : RequestResetResultEntity()
    data class Error(val error: ) : RequestResetResultEntity()
}
