package com.example.data.network.authentication

import com.example.data.network.models.responses.success.Tokens
import com.example.domain.utils.Endpoints
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST (Endpoints.REFRESH_ENDPOINT)
    suspend fun refreshToken (
        @Body refresh: String,
    ) : Response<Tokens>
}