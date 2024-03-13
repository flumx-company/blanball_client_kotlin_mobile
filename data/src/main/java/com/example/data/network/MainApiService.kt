package com.example.data.network


import com.example.data.network.models.*
import com.example.data.network.models.requests.AuthRequest
import com.example.data.network.models.requests.CreationAnEventRequest
import com.example.data.network.models.requests.EditEventByIdRequest
import com.example.data.network.models.requests.EditMyProfileRequest
import com.example.data.network.models.requests.PostEmailVerifyCodeRequest
import com.example.data.network.models.requests.RegistrationRequest
import com.example.data.network.models.requests.ResetCompleteRequest
import com.example.data.network.models.requests.SendEmailPasswordResetRequest
import com.example.data.network.models.requests.SendResetCodeRequest
import com.example.data.network.models.requests.UpdateUserProfileRequest
import com.example.data.network.models.responses.success.CreationAnEventResponse
import com.example.data.network.models.responses.success.EditEventByIdResponse
import com.example.data.network.models.responses.success.EditMyProfileResponse
import com.example.data.network.models.responses.success.GetAllEventResponse
import com.example.data.network.models.responses.success.GetEventByIdResponse
import com.example.data.network.models.responses.success.GetIsTechnicalWorkStatusResponse
import com.example.data.network.models.responses.success.GetMyEventsResponse
import com.example.data.network.models.responses.success.GetMyProfileResponse
import com.example.data.network.models.responses.success.GetRelevantUserSearchListResponse
import com.example.data.network.models.responses.success.GetUkraineCitiesListResponse
import com.example.data.network.models.responses.success.GetUserPlannedEventsByIdResponse
import com.example.data.network.models.responses.success.GetUserProfileByIdResponse
import com.example.data.network.models.responses.success.GetUserReviewsByIdResponse
import com.example.data.network.models.responses.success.GetUsersListResponse
import com.example.data.network.models.responses.success.JoinToEventAsFunResponse
import com.example.data.network.models.responses.success.JoinToEventAsPlayerResponse
import com.example.data.network.models.responses.success.LoginSuccess
import com.example.data.network.models.responses.success.PostEmailVerifyCodeResponse
import com.example.data.network.models.responses.success.RegistrationResponse
import com.example.data.network.models.responses.success.ResetCompleteResponse
import com.example.data.network.models.responses.success.SendCodeResponse
import com.example.data.network.models.responses.success.SendEmailPasswordResetSuccess
import com.example.data.network.models.responses.success.SendVerifyCodeToUserEmailResponse
import com.example.data.network.models.responses.success.UpdateUserProfileResponse
import com.example.domain.utils.Endpoints
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


interface MainApiService {

    @POST(Endpoints.LOGIN_ENDPOINT)
    suspend fun loginAuthorization(@Body authRequest: AuthRequest): LoginSuccess

    @POST(Endpoints.SEND_EMAIL_PASSWORD_RESET_ENDPOINT)
    suspend fun sendEmailPasswordReset(@Body sendEmailPasswordResetRequest: SendEmailPasswordResetRequest): SendEmailPasswordResetSuccess

    @POST(Endpoints.VALIDATE_RESET_CODE_ENDPOINT)
    suspend fun validateResetCode(@Body sendResetCodeRequest: SendResetCodeRequest): SendCodeResponse

    @POST(Endpoints.RESET_COMPLETE_ENDPOINT)
    suspend fun resetComplete(@Body resetCompleteRequest: ResetCompleteRequest): ResetCompleteResponse

    @POST(Endpoints.REGISTER_ENDPOINT)
    suspend fun userRegistration(@Body registrationRequest: RegistrationRequest): RegistrationResponse

    @GET(Endpoints.USER_PROFILE_ENDPOINT)
    suspend fun getUserProfileById(@Path("id") id: Int): GetUserProfileByIdResponse

    @GET(Endpoints.REVIEWS_ENDPOINT)
    suspend fun getUserReviewsById(
        @Path("id") id: Int,
        @Query("page") page: Int
    ): GetUserReviewsByIdResponse

    @GET(Endpoints.PLANNED_EVENTS_ENDPOINT)
    suspend fun getListOfUsersPlannedEvents(
        @Path("id") id: Int,
        @Query("page") page: Int
    ): GetUserPlannedEventsByIdResponse

    @PUT(Endpoints.UPDATE_PROFILE_ENDPOINT)
    suspend fun updateUserProfile(@Body updateUserProfileRequest: UpdateUserProfileRequest): UpdateUserProfileResponse

    @GET(Endpoints.ME_PROFILE_ENDPOINT)
    suspend fun getMyProfile(@Query("page") page: Int): GetMyProfileResponse

    @GET(Endpoints.GET_ALL_EVENTS_ENDPOINT)
    suspend fun getAllEvents(
        @Query("page") page: Int,
        @Query("type") typeOfSport: String,
        @Query("gender") gender: String,
        @Query("date_and_time") date_and_time: String,
        @Query("ordering") ordering: String,
        @Query("date_and_time_before") date_and_time_before: String,
        @Query("date_and_time_after") date_and_time_after: String,
    ): GetAllEventResponse

    @POST(Endpoints.CREATE_EVENT_ENDPOINT)
    suspend fun createAnEvent(@Body creationAnEventRequest: CreationAnEventRequest): CreationAnEventResponse

    @GET(Endpoints.GET_MY_EVENTS_ENDPOINT)
    suspend fun getMyEvents(
        @Query("page") page: Int,
        @Query("type") typeOfSport: String,
        @Query("gender") gender: String,
        @Query("date_and_time") date_and_time: String,
        @Query("ordering") ordering: String,
        @Query("date_and_time_before") date_and_time_before: String,
        @Query("date_and_time_after") date_and_time_after: String,
    ): GetMyEventsResponse

    @GET(Endpoints.USERS_LIST)
    suspend fun getUsersList(
        @Query("page") page: Int,
        @Query("profile__gender") profile__gender: String?,
        @Query("profile__age_min") profile__age_min: Int?,
        @Query("profile__age_max") profile__age_max: Int?,
        @Query("profile__position") profile__position: String?,
        @Query("ordering") ordering: String?,
    ): GetUsersListResponse

    @GET(Endpoints.GET_EVENT_ENDPOINT)
    suspend fun getEventById(
        @Path("id") id: Int,
    ): GetEventByIdResponse

    @PUT(Endpoints.EDIT_EVENT_ENDPOINT)
    suspend fun editEventById(
        @Body editEventByIdRequest: EditEventByIdRequest,
        @Path("id") id: Int
    ): EditEventByIdResponse

    @GET(Endpoints.VERIFY_EMAIL_REQUEST_ENDPOINT)
    suspend fun sendVerifyCodeToUserEmail(
        @Query("page") page: Int,
    ): SendVerifyCodeToUserEmailResponse

    @POST(Endpoints.POST_EMAIL_VERIFY_CODE_ENDPOINT)
    suspend fun postEmailVerifyCode(
        @Body postEmailVerifyCodeRequest: PostEmailVerifyCodeRequest
    ): PostEmailVerifyCodeResponse

    @PUT(Endpoints.EDIT_MY_PROFILE_ENDPOINT)
    suspend fun ediMyProfile(
        @Body editMyProfileRequest: EditMyProfileRequest,
    ): EditMyProfileResponse

    @GET(Endpoints.GET_IS_TECHNICAL_WORK_STATUS_ENDPOINT)
    suspend fun getIsTechnicalWorkStatus(): GetIsTechnicalWorkStatusResponse

    @GET(Endpoints.GET_RELEVANT_USER_SEARCH_LIST)
    suspend fun getRelevantUserSearchList(
        @Query("search") search: String,
        @Query("page") page: Int,
        @Query("skipids") skipids: String,
    ): GetRelevantUserSearchListResponse

    @GET(Endpoints.GET_UKRAINE_CITIES_ENDPOINT)
    suspend fun getUkraineCitiesList(): GetUkraineCitiesListResponse

    @POST(Endpoints.JOIN_TO_EVENT_AS_PLAYER)
    suspend fun joinToEventAsPlayer(
        @Body eventId: Int
    ): JoinToEventAsPlayerResponse

    @POST(Endpoints.JOIN_TO_EVENT_AS_FUN)
    suspend fun joinToEventAsFun(
        @Body eventId: Int
    ): JoinToEventAsFunResponse
}

