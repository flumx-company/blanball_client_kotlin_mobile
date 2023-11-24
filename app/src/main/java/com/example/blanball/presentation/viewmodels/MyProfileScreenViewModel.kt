package com.example.blanball.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.MyProfileScreensMainContract
import com.example.blanball.presentation.data.UiState
import com.example.data.datastore.useravatarurlmanager.UserAvatarUrlManager
import com.example.data.datastore.usernamemanager.UserNameManager
import com.example.data.datastore.userphonemanager.UserPhoneManager
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
class MyProfileScreenViewModel @Inject constructor(
    internal val getMyProfileUseCase: GetMyProfileUseCase,
    internal val userNameManager: UserNameManager,
    internal val userAvatarUrlManager: UserAvatarUrlManager,
    internal val phoneNumberManager: UserPhoneManager,
) : ViewModel() {
    private var job: Job? = null

    val defaultState
        get() = MyProfileScreensMainContract.State(
            state = MyProfileScreensMainContract.ScreenViewState.Idle,
        )

    val currentState: MyProfileScreensMainContract.State
        get() = uiState.value as MyProfileScreensMainContract.State

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(defaultState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun handleScreenState(screenViewState: MyProfileScreensMainContract.ScreenViewState) {
        when (screenViewState) {
            is MyProfileScreensMainContract.ScreenViewState.Loading -> {
                getMyProfile()
            }
            is MyProfileScreensMainContract.ScreenViewState.LoadingError -> {
            }
            else -> {}
        }
    }

    fun getMyProfile() {
        job = viewModelScope.launch(Dispatchers.IO) {
            getMyProfileUseCase.executeGetMyProfile(1).let {
                when (val result =
                    getMyProfileUseCase.executeGetMyProfile(1)) {
                    is GetMyProfileResultEntity.Success -> {
                        val myProfile = result.success.profile
                        userNameManager.safeUserName("${myProfile.name} ${myProfile.last_name}")
                        myProfile.avatar_url?.let { avatarUrl ->
                            userAvatarUrlManager.safeAvatarUrl(
                                avatarUrl
                            )
                        }
                        val userFullName = userNameManager.getUserName().firstOrNull()
                        val splittedFullName = userFullName?.split(" ")
                        val userAvatarUrl = userAvatarUrlManager.getAvatarUrl().firstOrNull()
                        val userNumber = phoneNumberManager.getUserPhone().firstOrNull()
                        setState {
                            copy(
                                myFirstNameText = mutableStateOf(
                                    splittedFullName?.get(0) ?: ""
                                ),
                                myLastNameText = mutableStateOf(
                                    splittedFullName?.get(1) ?: ""
                                ),
                                myAvatarUrl = mutableStateOf(userAvatarUrl ?: ""),
                                aboutMeText = mutableStateOf(result.success.profile.about_me ?: ""),
                                phoneText = mutableStateOf(userNumber ?: ""),
                                heightState = mutableStateOf(
                                    result.success?.profile?.height?.toString() ?: ""
                                ),
                                weightState = mutableStateOf(
                                    result.success?.profile?.weight?.toString() ?: ""
                                ),

                                workingLegState = mutableStateOf(
                                    result.success.profile.working_leg ?: ""
                                ),
                                positionState = mutableStateOf(
                                    result.success.profile.position ?: ""
                                ),
                                emailStringState = mutableStateOf(result.success.email ?: ""),
                                myGenderState = mutableStateOf(result.success.profile.gender ?: ""),
                                roleState = mutableStateOf(result.success.role ?: ""),
                                birthdayState = mutableStateOf(
                                    result.success.profile.birthday ?: ""
                                ),
                                placeState = mutableStateOf(
                                    result.success.profile.place?.place_name ?: ""
                                ),
                                state = MyProfileScreensMainContract.ScreenViewState.LoadingSuccess
                            )
                        }
                    }

                    is GetMyProfileResultEntity.Error -> {

                    }
                }
            }
        }
    }

    fun setState(reduce: MyProfileScreensMainContract.State.() -> MyProfileScreensMainContract.State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}