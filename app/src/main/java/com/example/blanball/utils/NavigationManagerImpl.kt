package com.example.blanball.utils

import com.example.domain.utils.NavigationManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class NavigationManagerImpl @Inject constructor() : NavigationManager {
    private val job = SupervisorJob()
    private val scope = CoroutineScope(job)

    override fun navigateToLogin() {
        scope.launch(Dispatchers.IO) {
            isNavigateToLogin.value = true
        }
    }

    override fun navigateToTechWorks() {
        scope.launch(Dispatchers.IO) {
            isNavigateToTechWorks.value = true
        }
    }
}