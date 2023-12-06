package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.PostEmailVerifyCodeErrorEntityDetail
import com.example.domain.entity.responses.PostEmailVerifyCodeResponseEntityData

sealed class PostEmailVerifyCodeResultEntity {
    data class Success(val data: PostEmailVerifyCodeResponseEntityData) : PostEmailVerifyCodeResultEntity()
    data class Error(val error: PostEmailVerifyCodeErrorEntityDetail) : PostEmailVerifyCodeResultEntity()
}
