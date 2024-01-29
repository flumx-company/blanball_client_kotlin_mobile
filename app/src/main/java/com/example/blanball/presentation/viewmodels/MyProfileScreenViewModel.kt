package com.example.blanball.presentation.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.MyProfileScreensMainContract
import com.example.blanball.presentation.data.UiEvent
import com.example.blanball.presentation.data.UiState
import com.example.blanball.utils.ext.extractDate
import com.example.blanball.utils.ext.extractWord
import com.example.blanball.utils.ext.formatPositionToEnglish
import com.example.blanball.utils.ext.formatRatingToFloat
import com.example.blanball.utils.ext.formatWorkingLegToEnglishWord
import com.example.data.datastore.useravatarurlmanager.UserAvatarUrlManager
import com.example.data.datastore.usernamemanager.UserNameManager
import com.example.data.datastore.userphonemanager.UserPhoneManager
import com.example.domain.entity.results.EditMyProfileResultEntity
import com.example.domain.entity.results.GetMyProfileResultEntity
import com.example.domain.entity.results.GetUkraineCitiesListResultEntity
import com.example.domain.usecases.interfaces.GetListOfUkraineCitiesUseCase
import com.example.domain.usecases.interfaces.GetMyProfileUseCase
import com.example.domain.usecases.interfaces.SendingRequestToChangeUserProfileUseCase
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
    internal val sendingRequestToChangeUserProfileUseCase: SendingRequestToChangeUserProfileUseCase,
    internal val getListOfUkraineCitiesUseCase: GetListOfUkraineCitiesUseCase,
    internal val userNameManager: UserNameManager,
    internal val userAvatarUrlManager: UserAvatarUrlManager,
    internal val phoneNumberManager: UserPhoneManager,
    private val application: Application
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

    fun handleScreenState(event: UiEvent) {
        when (event) {
            is MyProfileScreensMainContract.Event.GetUkraineCitiesList -> {
                getUkraineCitiesList()
            }
            is MyProfileScreensMainContract.Event.SendGetMyProfileRequest -> {
                getMyProfile()
            }
            is MyProfileScreensMainContract.Event.SendEditMyProfileRequest -> {
                updateMyProfile()
            }
            is MyProfileScreensMainContract.Event.UpdateCitiesForRegionList -> {
                filterCitiesFromRegion(currentState.selectedRegion.value)
            }
            else -> {}
        }
    }

    fun updateMyProfile() {
        job = viewModelScope.launch(Dispatchers.IO) {
            sendingRequestToChangeUserProfileUseCase.executeEditUserProfileRequest(
                phone = currentState.phoneText.value,
                emailRequestConfiguration = currentState.emailRadioButtonState.value,
                phoneRequestConfiguration = currentState.phoneNumberRadioButtonState.value,
                showReviewsRequestConfiguration = currentState.myReviewsRadioButtonState.value,
                about_me = currentState.aboutMeText.value,
                birthday = "${currentState.editYearBirthdayState.value}-${currentState.editMonthBirthdayState.value}-${currentState.editDayBirthdayState.value}",
                gender = currentState.myGenderState.value,
                height = currentState.heightState.value.toInt(),
                last_name = currentState.myLastNameText.value,
                name = currentState.myFirstNameText.value,
                position = currentState.positionState.value.formatPositionToEnglish(application.applicationContext),
                weight = currentState.weightState.value.toInt(),
                working_leg = currentState.workingLegState.value.formatWorkingLegToEnglishWord(application.applicationContext),
                lat = 0.0,
                lon = 0.0,
                place_name = "${currentState.selectedCity.value}, ${currentState.selectedRegion.value}",
            ).let { result ->
                when (result) {
                    is EditMyProfileResultEntity.Success ->
                        setState {
                            copy(
                                state = MyProfileScreensMainContract.ScreenViewState.EditProfileRequestSuccess,
                                myAvatarUrl = mutableStateOf(""),
                                myFirstNameText = mutableStateOf(""),
                                myLastNameText = mutableStateOf(""),
                                phoneNumberRadioButtonState = mutableStateOf(false),
                                emailRadioButtonState = mutableStateOf(false),
                                myReviewsRadioButtonState = mutableStateOf(false),
                                plannedEventsRadioButtonState = mutableStateOf(false),
                                aboutMeText = mutableStateOf(""),
                                phoneText = mutableStateOf(""),
                                heightState = mutableStateOf(""),
                                weightState = mutableStateOf(""),
                                workingLegState = mutableStateOf(""),
                                positionState = mutableStateOf(""),
                                selectedCity = mutableStateOf(""),
                                selectedRegion = mutableStateOf(""),
                                editDayBirthdayState = mutableStateOf(""),
                                editMonthBirthdayState = mutableStateOf(""),
                                editYearBirthdayState = mutableStateOf(""),
                                emailStringState = mutableStateOf(""),
                                myGenderState = mutableStateOf(""),
                                roleState = mutableStateOf(""),
                                birthdayState = mutableStateOf(""),
                                placeState = mutableStateOf(""),
                                ratingState = mutableStateOf(0f),
                                locationsData = mutableStateOf(
                                    emptyList()
                                ),
                                defaultCitiesForRegionList = mutableStateOf(
                                    emptyList()
                                ),
                                citiesForRegionList = mutableStateOf(
                                    emptyList()
                                ),
                                regionOfUkraineList = mutableStateOf(
                                    emptyList()
                                ),
                            )
                        }

                    is EditMyProfileResultEntity.Error ->
                        setState {
                            copy(
                                state = MyProfileScreensMainContract.ScreenViewState.EditProfileRequestError
                            )
                        }
                }
            }
        }
    }

    private fun getUkraineCitiesList() {
        job = viewModelScope.launch(Dispatchers.IO) {
            getListOfUkraineCitiesUseCase.executeGetListOfUkraineCities().let { result ->
                when (result) {
                    is GetUkraineCitiesListResultEntity.Success -> {
                        setState {
                            copy(
                                regionOfUkraineList = mutableStateOf(result.data.data.map { it.name }),
                                locationsData = mutableStateOf(result.data.data.map { it}),
                                citiesForRegionList = mutableStateOf(result.data.data.flatMap { it.cities.map { it.name } }),
                                defaultCitiesForRegionList =  mutableStateOf(result.data.data.flatMap { it.cities.map { it.name } }),
                            )
                        }
                    }
                    is GetUkraineCitiesListResultEntity.Error -> {
                    }
                }
            }
        }
    }

    private fun filterCitiesFromRegion(selectedRegion: String) {
        job = viewModelScope.launch(Dispatchers.IO) {
            currentState.citiesForRegionList.value = currentState.defaultCitiesForRegionList.value
            val citiesForRegionList = currentState.citiesForRegionList.value.filter {city ->
                currentState.locationsData.value.any() { it.name == selectedRegion && city in it.cities.map { cityItem -> cityItem.name } }
            }
            currentState.selectedCity.value = citiesForRegionList[0]
            setState {
                copy(
                    citiesForRegionList = mutableStateOf(citiesForRegionList),
                )
            }
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
                                emailRadioButtonState = mutableStateOf(result.success.configuration.email),
                                myReviewsRadioButtonState = mutableStateOf(result.success.configuration.show_reviews),
                                phoneNumberRadioButtonState = mutableStateOf(result.success.configuration.phone),
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
                                editDayBirthdayState = mutableStateOf(
                                    result.success.profile.birthday?.extractDate(index = 2) ?: ""
                                ),
                                editMonthBirthdayState = mutableStateOf(
                                    result.success.profile.birthday?.extractDate(index = 1) ?: ""
                                ) ,
                                editYearBirthdayState = mutableStateOf(
                                    result.success.profile.birthday?.extractDate(index = 0) ?: ""
                                ),
                                placeState = mutableStateOf(
                                    result.success.profile.place?.place_name ?: ""
                                ),
                                ratingState = mutableStateOf(
                                    result.success.raiting?.formatRatingToFloat() ?: 0f
                                ),
                                selectedCity = mutableStateOf(
                                    result.success.profile.place?.place_name?.extractWord(wordIndex = 0) ?: ""
                                ),
                                selectedRegion =  mutableStateOf(
                                    result.success.profile.place?.place_name?.extractWord(wordIndex = 1) ?: ""
                                ),
                                state = MyProfileScreensMainContract.ScreenViewState.LoadingSuccess,
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