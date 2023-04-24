package com.example.blanball.utils

import androidx.navigation.NavController
import com.example.domain.events.AuthEvent
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class AuthEventHandler(private val navController: NavController) {
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAuthEvent(event: AuthEvent) {
        val isAuthenticated = event.isAuthenticated()
        if (!isAuthenticated) {
//            navController.navigate(R.id.loginFragment)
//   TODO: This EventBus library doesnt work with Jetpack Compose
        }
    }
}