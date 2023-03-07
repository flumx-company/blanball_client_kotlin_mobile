package com.example.data.backend

import okhttp3.Interceptor
import okhttp3.Response

    class AuthInterceptor(private val tokenProvider: TokenProvider) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()

            val token = tokenProvider.getToken()

            val requestWithToken = originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()

            val response = chain.proceed(requestWithToken)

            if (response.code == 401) {

                tokenProvider.refreshToken()

                val newToken = tokenProvider.getToken()
                val requestWithNewToken = originalRequest.newBuilder()
                    .header("Authorization", "Bearer $newToken")
                    .build()

                return chain.proceed(requestWithNewToken)
            }

            return response
        }
    }