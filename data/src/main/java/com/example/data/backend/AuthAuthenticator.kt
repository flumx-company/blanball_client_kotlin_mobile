package com.example.data.backend

import com.example.data.datastore.tokenmanager.TokenManager
import com.example.domain.events.AuthEvent
import com.example.domain.utils.Code
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {
            tokenManager.getToken().first()
        }
        if (response.code == Code.CODE_401){
            val authEvent = AuthEvent(false)
            EventBus.getDefault().post(authEvent)
            return null
        }
        return response.request.newBuilder()
            .addHeader("Authorization", "")
            .build()
        }
    }