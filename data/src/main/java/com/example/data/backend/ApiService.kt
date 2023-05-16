package com.example.data.backend


import com.example.data.backend.models.*
import com.example.data.backend.models.requests.AuthRequest
import com.example.data.backend.models.requests.RegistrationRequest
import com.example.data.backend.models.requests.ResetCompleteRequest
import com.example.data.backend.models.requests.SendEmailPasswordResetRequest
import com.example.data.backend.models.requests.SendResetCodeRequest
import com.example.data.backend.models.responses.GetListOfUsersEventsResponse
import com.example.data.backend.models.responses.GetUserProfileByIdResponse
import com.example.data.backend.models.responses.GetUserReviewsByIdResponse
import com.example.data.backend.models.responses.LoginSuccess
import com.example.data.backend.models.responses.RegistrationResponse
import com.example.data.backend.models.responses.ResetCompleteResponse
import com.example.data.backend.models.responses.SendCodeResponse
import com.example.data.backend.models.responses.SendEmailPasswordResetSuccess
import com.example.domain.utils.Endpoints
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService  {

    @POST(Endpoints.LOGIN_ENDPOINT)
    suspend fun loginAuthorization(@Body authRequest: AuthRequest): LoginSuccess

    @POST(Endpoints.SEND_EMAIL_PASSWORD_RESET_ENDPOINT)
    suspend fun sendEmailPasswordReset(@Body sendEmailPasswordResetRequest: SendEmailPasswordResetRequest) : SendEmailPasswordResetSuccess

    @POST(Endpoints.VALIDATE_RESET_CODE_ENDPOINT)
    suspend fun validateResetCode(@Body sendResetCodeRequest: SendResetCodeRequest): SendCodeResponse

    @POST (Endpoints.RESET_COMPLETE_ENDPOINT)
    suspend fun resetComplete(@Body resetCompleteRequest: ResetCompleteRequest): ResetCompleteResponse

    @POST (Endpoints.REGISTER_ENDPOINT)
    suspend fun userRegistration(@Body registrationRequest: RegistrationRequest): RegistrationResponse

    @GET(Endpoints.USER_PROFILE_ENDPOINT)
    suspend fun getUserProfileById(@Path ("id") id: Int): GetUserProfileByIdResponse

    @GET (Endpoints.REVIEWS_ENDPOINT)
    suspend fun getUserReviewsById(@Path ("id") id: Int): GetUserReviewsByIdResponse

    @GET (Endpoints.PLANNED_EVENTS)
    suspend fun  getListOfUsersEvents (@Path ("id") id: Int): GetListOfUsersEventsResponse
}