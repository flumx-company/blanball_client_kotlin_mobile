package com.example.data.network.authentication

import com.example.data.network.models.responses.success.Tokens
import com.example.data.datastore.tokenmanager.TokenManager
import com.example.domain.utils.Endpoints
import com.example.domain.utils.NavigationManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val navigationManager: NavigationManager,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {
            tokenManager.getRefreshToken().first()
        }
        val newTokens = runBlocking {
            getNewToken(token)
        }
        return if (newTokens.code() == 401 ) {
            runBlocking {
                tokenManager.deleteRefreshToken()
                tokenManager.deleteAccessToken()
                navigationManager.navigateToLogin()
            }
            null
        } else {
            newTokens.body()?.let {
                runBlocking {
                    tokenManager.saveAccessToken(it.access)
                }
                response.request.newBuilder()
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer ${it.access}")
                    .build()
            }
        }
    }

    private suspend fun getNewToken(refreshToken: String?): retrofit2.Response<Tokens> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Endpoints.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
        val service = retrofit.create(AuthApiService::class.java)
        return service.refreshToken(refresh = refreshToken.toString())
    }
}