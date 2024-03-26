package com.example.data.network.models.responses.success

import com.squareup.moshi.Json

data class GetPrivateRequestListResponse(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: GetPrivateRequestListResponseData,
    @Json (name = "message") val message: String? = null,
    @Json (name = "status") val status: String
)

data class GetPrivateRequestListResponseData(
    @Json (name = "current_page") val current_page: Int,
    @Json (name = "next") val next: Any,
    @Json (name = "page_size") val page_size: Int,
    @Json (name = "previous") val previous: Any,
    @Json (name = "results") val results: List<GetPrivateRequestListResponseResult>,
    @Json (name = "success") val success: Boolean,
    @Json (name = "total_count") val total_count: Int
)

data class GetPrivateRequestListResponseResult(
    @Json (name = "event") val event: GetPrivateRequestListResponseEvent,
    @Json (name = "id") val id: Int,
    @Json (name = "recipient") val recipient: Int,
    @Json (name = "sender") val sender: GetPrivateRequestListResponseSender,
    @Json (name = "status") val status: String,
    @Json (name = "time_created") val time_created: String
)

data class GetPrivateRequestListResponseEvent(
    @Json (name = "date_and_time") val date_and_time: String,
    @Json (name = "id") val id: Int,
    @Json (name = "name") val name: String
)

data class GetPrivateRequestListResponseSender(
    @Json (name = "id") val id: Int,
    @Json (name = "profile") val profile: GetPrivateRequestListResponseProfile,
    @Json (name = "raiting") val raiting: Any? = null,
)

data class GetPrivateRequestListResponseProfile(
    @Json (name = "avatar_url") val avatar_url: String? = null,
    @Json (name = "last_name") val last_name: String,
    @Json (name = "name") val name: String,
    @Json (name = "position") val position: String? = null,
    @Json (name = "working_leg") val working_leg: String
)