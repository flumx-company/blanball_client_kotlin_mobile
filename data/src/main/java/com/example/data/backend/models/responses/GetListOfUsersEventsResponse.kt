package com.example.data.backend.models.responses

data class GetListOfUsersEventsResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<ResultOfGetListOfUsersEventsRequest>
)

data class ResultOfGetListOfUsersEventsRequest(
    val amount_members: Int,
    val author: GetListOfUsersEventsRequestAuthor,
    val count_current_fans: String,
    val count_current_users: String,
    val date_and_time: String,
    val description: String,
    val duration: Int,
    val forms: Forms,
    val gender: String,
    val id: Int,
    val name: String,
    val need_ball: Boolean,
    val need_form: Boolean,
    val place: Place,
    val price: Int,
    val privacy: Boolean,
    val request_user_role: String,
    val status: String,
    val type: String
)

data class GetListOfUsersEventsRequestAuthor(
    val id: Int,
    val phone: String,
    val profile: Profile
)

class Forms

data class Place(
    val lat: Int,
    val lon: Int,
    val place_name: String
)

data class Profile(
    val avatar_url: String,
    val id: Int,
    val last_name: String,
    val name: String
)
