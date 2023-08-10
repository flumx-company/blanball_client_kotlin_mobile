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
     const val PLANNED_EVENTS_ENDPOINT = BuildConfig.PLANNED_EVENTS
     const val UPDATE_PROFILE_ENDPOINT = BuildConfig.UPDATE_PROFILE_ENDPOINT
     const val REFRESH_ENDPOINT = BuildConfig.UPDATE_PROFILE_ENDPOINT
     const val USERS_LIST = BuildConfig.USERS_LIST
     const val FLUM_X_URL = BuildConfig.FLUM_X_URL
     const val ME_PROFILE_ENDPOINT = BuildConfig.ME_PROFILE_ENDPOINT
     }
object Code {
     const val CODE_401 = BuildConfig.CODE_401
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
     const val FIRST_OLDER = "id"
     const val FIRST_NEW = "-id"
}

object Integers {
     const val ZERO = 0
     const val ONE = 1
     const val FIVE = 5
     const val SIX = 6
     const val NINE = 9
     const val EIGHTY = 80
     const val THIRTY = 30
     const val ONE_HUNDRED_AND_FORTY_FIVE = 145
     const val TWO_HUNDRED_AND_TEN = 210
     const val DURATION_MILLIS_ON_CARD = 1000
}

object Formats {
     const val REVIEW_DATE_FORMAT_INPUT = "yyyy-MM-dd'T'HH:mm:ss.SSSSS"
     const val REVIEW_DATE_FORMAT_OUTPUT = "dd MMMM"
     const val EVENTS_DATE_FORMAT_INPUT = "yyyy-MM-dd'T'HH:mm:ss"
     const val EVENTS_DATE_FORMAT_OUTPUT = "dd MMMM"
     const val EVENTS_DATE_FORMAT_TO_TIME_OUTPUT = "HH:mm"
     const val DECIMAL_FORMAT = "0.0"
     const val EMAIl_FORMAT = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"
     const val PHONE_MASK = "##-###-##-##"
}