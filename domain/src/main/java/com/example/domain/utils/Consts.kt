package com.example.domain.utils

import com.example.domain.BuildConfig

object Endpoints {
     const val BASE_URL = BuildConfig.BASE_URL
     const val LOGIN_ENDPOINT = BuildConfig.LOGIN_ENDPOINT
     const val SEND_EMAIL_PASSWORD_RESET_ENDPOINT = BuildConfig.PASSWORD_RESET_ENDPOINT
     const val VALIDATE_RESET_CODE_ENDPOINT = BuildConfig.VALIDATE_RESET_CODE_ENDPOINT
     const val RESET_COMPLETE_ENDPOINT = BuildConfig.RESET_COMPLETE_ENDPOINT
     const val REGISTER_ENDPOINT = BuildConfig.REGISTER_ENDPOINT
     const val PRIVACY_POLICY_URL = BuildConfig.PRIVACY_POLICY_URL
     const val USER_PROFILE_ENDPOINT = BuildConfig.USER_PROFILE_ENDPOINT
     const val REVIEWS_ENDPOINT = BuildConfig.REVIEWS_ENDPOINT
     const val PLANNED_EVENTS = BuildConfig.PLANNED_EVENTS
     }
object Code {
     const val CODE_401 = BuildConfig.CODE_401
}
object Strings {
     const val MAN = "Man"
     const val WOMAN = "Woman"
}