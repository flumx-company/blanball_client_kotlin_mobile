package com.example.blanball.utils

import com.example.domain.utils.NavigationManager
import javax.inject.Inject

class NavigationManagerImpl @Inject constructor() : NavigationManager {
    override fun navigateToLogin() {
        navigateToLogin.value = true
    }
}
