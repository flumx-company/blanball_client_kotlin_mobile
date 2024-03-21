package com.example.domain.entity.results

import com.example.domain.entity.responses.success.PostEmailVerifyCodeResponseEntityData
import com.example.domain.entity.responses.errors.PostEmailVerifyCodeErrorEntityDetail

sealed class PostEmailVerifyCodeResult {
    data class Success(val data: PostEmailVerifyCodeResponseEntityData) : PostEmailVerifyCodeResult()
    data class Error(val error: PostEmailVerifyCodeErrorEntityDetail) : PostEmailVerifyCodeResult()
}