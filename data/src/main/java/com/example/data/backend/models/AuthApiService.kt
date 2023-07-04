package com.example.data.backend.models

import com.example.data.backend.models.responses.Tokens
import com.example.domain.utils.Endpoints
import retrofit2.Response
import retrofit2.http.POST

interface AuthApiService {

    @POST (Endpoints.REFRESH_ENDPOINT)
    suspend fun refreshToken (
        token: String,
    ) : Response<Tokens>
}