package com.example.domain.entity.responses.success

data class GetPrivateRequestListResponseEntity(
    val code: Int,
    val `data`: GetPrivateRequestListResponseEntityData,
    val message: String? = null,
    val status: String
)

data class GetPrivateRequestListResponseEntityData(
    val current_page: Int,
    val next: Any,
    val page_size: Int,
    val previous: Any,
    val results: List<GetPrivateRequestListResponseEntityResult>,
    val success: Boolean,
    val total_count: Int
)

data class GetPrivateRequestListResponseEntityResult(
    val event: GetPrivateRequestListResponseEntityEvent,
    val id: Int,
    val recipient: Int,
    val sender: GetPrivateRequestListResponseEntitySender,
    val status: String,
    val time_created: String
)

data class GetPrivateRequestListResponseEntityEvent(
    val date_and_time: String,
    val id: Int,
    val name: String
)

data class GetPrivateRequestListResponseEntitySender(
    val id: Int,
    val profile: GetPrivateRequestListResponseEntityProfile,
    val raiting: Any
)

data class GetPrivateRequestListResponseEntityProfile(
    val avatar_url: String? = null,
    val last_name: String,
    val name: String,
    val position: String? = null,
    val working_leg: String
)