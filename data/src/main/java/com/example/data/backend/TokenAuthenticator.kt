package com.example.data.backend

import com.example.data.datastore.TokenManager
import com.example.domain.utils.Const
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator (private val tokenManager: TokenManager) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {
            tokenManager.getToken().firstOrNull()
        }
        if (response.code == Const.CODE_401){
            return null
                //navigation
        }
        return response.request.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        }
    }