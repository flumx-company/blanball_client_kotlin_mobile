package com.example.data.backend.models.responses.success

import com.squareup.moshi.Json

data class GetMyEventsResponse(
    @Json(name = "code") val code: Int,
    @Json(name = "`data`") val `data`: GetMyEventsResponseData,
    @Json(name = "message") val message: String? = null,
    @Json(name = "status") val status: String
)

data class GetMyEventsResponseData(
    @Json(name = "current_page") val current_page: Int,
    @Json(name = "next") val next: String? = null,
    @Json(name = "page_size") val page_size: Int,
    @Json(name = "previous") val previous: Any? = null,
    @Json(name = "results") val results: List<GetMyEventsResponseResult>,
    @Json(name = "success") val success: Boolean,
    @Json(name = "total_count") val total_count: Int
)

data class GetMyEventsResponseResult(
    @Json(name = "amount_members") val amount_members: Int? = null,
    @Json(name = "author") val author: GetMyEventsResponseAuthor,
    @Json(name = "count_current_fans") val count_current_fans: Int? = null,
    @Json(name = "count_current_users") val count_current_users: Int? = null,
    @Json(name = "date_and_time") val date_and_time: String,
    @Json(name = "description") val description: String,
    @Json(name = "duration") val duration: Int,
    @Json(name = "forms") val forms: Any? = null,
    @Json(name = "gender") val gender: String,
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "need_ball") val need_ball: Boolean,
    @Json(name = "need_form") val need_form: Boolean,
    @Json(name = "pk_user_role") val pk_user_role: Any? = null,
    @Json(name = "place") val place: GetMyEventsResponsePlace,
    @Json(name = "price") val price: Int? = null,
    @Json(name = "privacy") val privacy: Boolean,
    @Json(name = "request_user_role") val request_user_role: Any? = null,
    @Json(name = "status") val status: String? = null,
    @Json(name = "type") val type: String
)

data class GetMyEventsResponseAuthor(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "phone") val phone: String,
    @Json(name = "profile") val profile: GetMyEventsResponseProfile
)

data class GetMyEventsResponsePlace(
    @Json(name = "lat") val lat: Double,
    @Json(name = "lon") val lon: Double,
    @Json(name = "place_name") val place_name: String
)

data class GetMyEventsResponseProfile(
    @Json(name = "avatar_url") val avatar_url: Any? = null,
    @Json(name = "id") val id: Int,
    @Json(name = "last_name") val last_name: String,
    @Json(name = "name") val name: String
)