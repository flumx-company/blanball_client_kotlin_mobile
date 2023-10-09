package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class GetAllEventResponse(
    @Json (name = "code" ) val code: Int,
    @Json (name = "`data`" ) val `data`: GetAllEventResponseData,
    @Json (name = "message" ) val message: String? = null,
    @Json (name = "status" ) val status: String
)

data class GetAllEventResponseData(
    @Json (name = "current_page" ) val current_page: Int,
    @Json (name = "next" ) val next: String,
    @Json (name = "page_size" ) val page_size: Int,
    @Json (name = "previous" ) val previous: Any,
    @Json (name = "results" ) val results: List<GetAllEventResponseResult>,
    @Json (name = "success" ) val success: Boolean,
    @Json (name = "total_count" ) val total_count: Int
)

data class GetAllEventResponseResult(
    @Json (name = "amount_members" ) val amount_members: Int,
    @Json (name = "author" ) val author: GetAllEventResponseAuthor,
    @Json (name = "count_current_fans" ) val count_current_fans: Int,
    @Json (name = "count_current_users" ) val count_current_users: Int,
    @Json (name = "date_and_time" ) val date_and_time: String,
    @Json (name = "description" ) val description: String,
    @Json (name = "duration" ) val duration: Int,
    @Json (name = "forms" ) val forms: Any,
    @Json (name = "gender" ) val gender: String,
    @Json (name = "id" ) val id: Int,
    @Json (name = "name" ) val name: String,
    @Json (name = "need_ball" ) val need_ball: Boolean,
    @Json (name = "need_form" ) val need_form: Boolean,
    @Json (name = "pk_user_role" ) val pk_user_role: Any,
    @Json (name = "place" ) val place: GetAllEventResponsePlace,
    @Json (name = "price" ) val price: Int,
    @Json (name = "privacy" ) val privacy: Boolean,
    @Json (name = "request_user_role" ) val request_user_role: Any,
    @Json (name = "status" ) val status: String,
    @Json (name = "type" ) val type: String
)

data class GetAllEventResponseAuthor(
    @Json (name = "id" ) val id: Int,
    @Json (name = "phone" ) val phone: String,
    @Json (name = "profile" ) val profile: GetAllEventResponseProfile
)

data class GetAllEventResponsePlace(
    @Json (name = "lat" ) val lat: Int,
    @Json (name = "lon" ) val lon: Int,
    @Json (name = "place_name" ) val place_name: String
)

data class GetAllEventResponseProfile(
    @Json (name = "avatar_url" ) val avatar_url: Any,
    @Json (name = "id" ) val id: Int,
    @Json (name = "last_name" ) val last_name: String,
    @Json (name = "name" ) val name: String
)