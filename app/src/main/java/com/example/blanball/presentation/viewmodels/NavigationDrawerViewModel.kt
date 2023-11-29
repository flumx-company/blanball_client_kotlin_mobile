package com.example.blanball.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.NavigationDrawerMainContract
import com.example.blanball.presentation.data.UiState
import com.example.data.datastore.useravatarurlmanager.UserAvatarUrlManager
import com.example.data.datastore.usernamemanager.UserNameManager
import com.example.domain.entity.results.GetMyProfileResultEntity
import com.example.domain.usecases.interfaces.GetMyProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NavigationDrawerViewModel
@Inject constructor(
    internal val getMyProfileUseCase: GetMyProfileUseCase,
    internal val userNameManager: UserNameManager,
    internal val userAvatarUrlManager: UserAvatarUrlManager,
) : ViewModel()
{
    private var job: Job? = null

    val defaultState
        get() = NavigationDrawerMainContract.State(
            state = NavigationDrawerMainContract.ScreenViewState.Idle,
        )

    val currentState: NavigationDrawerMainContract.State
        get() = uiState.value as NavigationDrawerMainContract.State

    private val _uiState : MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

   fun getMyProfile() {
        job = viewModelScope.launch(Dispatchers.IO) {
            getMyProfileUseCase.executeGetMyProfile(1).let {
                when( val result =
                    getMyProfileUseCase.executeGetMyProfile(1 )) {
                    is GetMyProfileResultEntity.Success -> {

                        val myProfile = result.success.profile
                        userNameManager.safeUserName("${myProfile.name} ${myProfile.last_name}")
                        myProfile.avatar_url?.let { avatarUrl -> userAvatarUrlManager.safeAvatarUrl(avatarUrl) }
                        val userFullName = userNameManager.getUserName().firstOrNull()
                        val splittedFullName = userFullName?.split(" ")
                        val userAvatarUrl = userAvatarUrlManager.getAvatarUrl().firstOrNull()
                         setState {
                             copy(
                                 userFirstNameText = mutableStateOf(splittedFullName?.get(0) ?: "--"),
                                 userLastNameText = mutableStateOf(splittedFullName?.get(1) ?: "--"),
                                 userAvatar = mutableStateOf(userAvatarUrl)
                             )
                         }
                    }
                    is GetMyProfileResultEntity.Error -> {

                    }
                }
            }
        }
    }

    fun setState(reduce: NavigationDrawerMainContract.State.() -> NavigationDrawerMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}