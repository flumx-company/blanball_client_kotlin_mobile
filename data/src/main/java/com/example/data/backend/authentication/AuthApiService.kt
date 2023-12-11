package com.example.data.backend.authentication

import com.example.data.backend.models.responses.success.Tokens
import com.example.domain.utils.Endpoints
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST (Endpoints.REFRESH_ENDPOINT)
    suspend fun refreshToken (
        @Body token: String,
    ) : Response<Tokens>
}