package com.example.data.backend

import com.example.data.backend.models.AuthRequest
import com.example.data.backend.models.AuthResponseDto
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


val authenticator =
    AccessTokenAuthenticator("JD6w4FUsAN8Xfidd2c2jqsbWhpHYgumESIdPeNPUrjzIJLL2qeosy9PMvV7HrqAL9d1OFeMm10TRYNLrVjhRmIWYeawnavsEA26A7DCEQN2lzWFxoIebqhfftiVs1gBXaTTNtpCrdjNbr2e2yyWmRpGg3zfwyuAV0Z9HaTJh8RtybBckMYrRtVXhvrVBVFcjKYbEFcdq0vYLAVxpVcbvwovBLGomNKt8tgMmfBBIM0ItD0hmRK0bK5RL3ACvRVE")

private val retrofit = Retrofit.Builder()
    .baseUrl("178.151.201.167:49299/api/v1" )
    .addConverterFactory(MoshiConverterFactory.create())
    .client(getRetrofitClient(authenticator))
    .build()

class AccessTokenAuthenticator(private val airtableApiToken: String) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.authenticatedWithSameToken(airtableApiToken)) {
            return null
        }
        return response.request.newBuilder()
            .addHeader("Authorization", "Bearer $airtableApiToken")
            .addHeader("Accept", "application/json")
            .build()
    }

    private fun Response.authenticatedWithSameToken(token: String): Boolean =
        header("Authorization with same token", "")?.endsWith(token) ?: true
}

private fun getRetrofitClient(authenticator: Authenticator? = null): OkHttpClient {

    return OkHttpClient.Builder()
        .addInterceptor { chain ->
            chain.proceed(chain.request().newBuilder().also {
                it.addHeader("Accept", "application/json")
            }.build())
        }.also { client ->
            authenticator?.let { client.authenticator(it) }
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            client.addInterceptor(logging)
        }.build()
}

enum class ApiEndpoint(val url: String) {
    LOGIN("/authentication/client/login"),
}


abstract class ApiService{

    @POST("/authentication/client/login")
    abstract suspend fun loginAuthorization(@Body authRequest: AuthRequest): AuthResponseDto

    object ApiObject {
        val retrofitService: ApiService = retrofit.create(ApiService::class.java)
    }
}