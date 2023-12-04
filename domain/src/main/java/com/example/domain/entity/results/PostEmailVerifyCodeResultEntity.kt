package com.example.domain.entity.results

import com.example.domain.entity.responses.PostEmailVerifyCodeErrorEntityDetail
import com.example.domain.entity.responses.PostEmailVerifyCodeResponseEntityData
import com.example.domain.entity.responses.SendVerifyCodeToUserEmailErrorEntityDataDetail
import com.example.domain.entity.responses.SendVerifyCodeToUserEmailResponseEntityData

sealed class PostEmailVerifyCodeResultEntity {
    data class Success(val data: PostEmailVerifyCodeResponseEntityData) : PostEmailVerifyCodeResultEntity()
    data class Error(val error: PostEmailVerifyCodeErrorEntityDetail) : PostEmailVerifyCodeResultEntity()
}
