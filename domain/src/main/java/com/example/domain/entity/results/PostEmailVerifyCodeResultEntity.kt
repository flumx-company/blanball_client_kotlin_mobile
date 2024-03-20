package com.example.domain.entity.results

import com.example.domain.entity.responses.success.PostEmailVerifyCodeResponseEntityData
import com.example.domain.entity.responses.errors.PostEmailVerifyCodeErrorEntityDetail

sealed class PostEmailVerifyCodeResultEntity {
    data class Success(val data: PostEmailVerifyCodeResponseEntityData) : PostEmailVerifyCodeResultEntity()
    data class Error(val error: PostEmailVerifyCodeErrorEntityDetail) : PostEmailVerifyCodeResultEntity()
}