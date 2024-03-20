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
    @Json (name = "results") val results: List<Any>,
    @Json (name = "success") val success: Boolean,
    @Json (name = "total_count") val total_count: Int
)