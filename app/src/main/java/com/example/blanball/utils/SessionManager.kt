package com.example.blanball.utils

import com.example.data.datastore.emailverificationmanager.EmailVerificationManager
import com.example.data.datastore.remembermemanager.RememberMeManager
import com.example.data.datastore.tokenmanager.TokenManager
import com.example.data.datastore.useravatarurlmanager.UserAvatarUrlManager
import com.example.data.datastore.useremailmanager.UserEmailManager
import com.example.data.datastore.useridmanager.UserIdManager
import com.example.data.datastore.usernamemanager.UserNameManager
import com.example.data.datastore.userphonemanager.UserPhoneManager
import com.example.data.datastore.verifycodemanager.ResetPassVerifyCodeManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first


class SessionManager(
    private var rememberMeManager: RememberMeManager,
    private var tokenManager: TokenManager,
    private var userNameManager: UserNameManager,
    private var userAvatarUrlManager: UserAvatarUrlManager,
    private var userPhoneManager: UserPhoneManager,
    private var resetPassVerifyCodeManager: ResetPassVerifyCodeManager,
    private var userEmailManager: UserEmailManager,
    private var emailVerificationManager: EmailVerificationManager,
    private var userIdManager: UserIdManager,
) {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(job)

    internal fun cleanDataStoreWithChecking() = scope.async(Dispatchers.IO) {
        if (rememberMeManager.getRememberMeFlag().first() == false) {
            tokenManager.deleteRefreshToken()
            tokenManager.deleteAccessToken()
            userIdManager.deleteUserId()
            rememberMeManager.deleteRememberMeFlag()
            userAvatarUrlManager.deleteAvatarUrl()
            userNameManager.deleteUserName()
            userPhoneManager.deleteUserPhone()
            resetPassVerifyCodeManager.deleteResetPassVerifyCode()
            userEmailManager.deleteUserEmail()
            emailVerificationManager.deleteIsEmailVerifiedState()
        }
        scope.cancel()
    }

    internal fun cleanDataStore() = scope.async(Dispatchers.IO) {
        tokenManager.deleteRefreshToken()
        tokenManager.deleteAccessToken()
        userIdManager.deleteUserId()
        rememberMeManager.deleteRememberMeFlag()
        userAvatarUrlManager.deleteAvatarUrl()
        userNameManager.deleteUserName()
        userPhoneManager.deleteUserPhone()
        resetPassVerifyCodeManager.deleteResetPassVerifyCode()
        userEmailManager.deleteUserEmail()
        emailVerificationManager.deleteIsEmailVerifiedState()
        scope.cancel()
    }
}