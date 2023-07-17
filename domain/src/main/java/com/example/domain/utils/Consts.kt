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
     const val USERS_LIST = BuildConfig.USERS_LIST
     }
object Code {
     const val CODE_401 = BuildConfig.CODE_401
}

object Integers {
     const val ONE = 1
     const val DURATION_MILLIS_ON_CARD = 1000
}

object Strings {
     const val MALE = "Man"
     const val FEMALE = "Woman"
     const val GK = "GK"
     const val LB = "LB"
     const val RB = "RB"
     const val CB = "CB"
     const val LWB = "LWB"
     const val RWB = "RWB"
     const val CDM = "CDM"
     const val CM = "CM"
     const val CAM = "CAM"
     const val RM = "RM"
     const val LM = "LM"
     const val RW = "RW"
     const val LW = "LW"
     const val RF = "RF"
     const val CF = "CF"
     const val LF = "LF"
     const val ST = "ST"
     const val FIRST_OLDER = "-id"
}

object Formats {
     const val REVIEW_DATE_FORMAT_INPUT = "yyyy-MM-dd'T'HH:mm:ss.SSSSS"
     const val REVIEW_DATE_FORMAT_OUTPUT = "dd MMMM"
     const val EVENTS_DATE_FORMAT_INPUT = "yyyy-MM-dd'T'HH:mm:ss"
     const val EVENTS_DATE_FORMAT_OUTPUT = "dd MMMM"
     const val EVENTS_DATE_FORMAT_TO_TIME_OUTPUT = "HH:mm"
     const val DECIMAL_FORMAT = "0.0"
     const val EMAIl_FORMAT = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"
}