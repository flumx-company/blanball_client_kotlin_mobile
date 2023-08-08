package com.example.blanball.utils.workers

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.RatingUsersMainContract
import com.example.blanball.presentation.viewmodels.UsersRatingViewModel
import com.example.blanball.utils.ext.convertToPositionCode
import com.example.domain.entity.results.GetUsersListResultEntity
import com.example.domain.usecases.interfaces.GetUsersListUseCase
import com.example.domain.utils.Integers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoadUsersWorkerImpl @Inject constructor (
   private val application: Application,
) : LoadUsersWorker {

    private var job: Job? = null
    private var page = Integers.ONE

   override fun resetPage() {
        page = Integers.ONE
    }

  override fun loadUsersList(
        viewModel: UsersRatingViewModel,
        getUsersListUseCase: GetUsersListUseCase,
        currentState: RatingUsersMainContract.State
        ) {
       job = viewModel.viewModelScope.launch(Dispatchers.IO) {
            val gender = currentState.genderSelectionState.value.stringValue
            val ageMin = currentState.ageSliderPosition.value.start.toInt()
            val ageMax = currentState.ageSliderPosition.value.endInclusive.toInt()
            val ordering = currentState.usersOrderingSelectionState.value.stringValue
            val position = currentState.positionSelectedItem.value.convertToPositionCode(application.applicationContext)

            when (val result = getUsersListUseCase.executeGetUsersList(
                page = page,
                gender = gender,
                age_min = ageMin,
                age_max = ageMax,
                ordering = ordering,
                position = position,
            )) {
                is GetUsersListResultEntity.Success -> {
                    val users = result.data.results
                    users?.let {
                        viewModel.setState {
                            copy(
                                usersList = mutableStateOf(currentState.usersList.value + it),
                                isLoadingMoreUsers = false,
                                userCounter = mutableStateOf(result.data.total_count),
                                state = RatingUsersMainContract.ScreenViewState.LoadingSuccess,
                            )
                        }
                    }
                    val nextPage = result.data.next
                    if (nextPage.isNullOrEmpty()) {
                        viewModel.setState {
                            copy(allUsersLoaded = true)
                        }
                    }
                }
                is GetUsersListResultEntity.Error -> {
                    viewModel.setState {
                        copy(
                            state = RatingUsersMainContract.ScreenViewState.LoadingError,
                        )
                    }
                }
            }
        }
    }

   override fun loadMoreUsers(
        viewModel: UsersRatingViewModel,
        getUsersListUseCase: GetUsersListUseCase,
        currentState: RatingUsersMainContract.State
        ) {
        if (!(currentState.isLoadingMoreUsers || currentState.allUsersLoaded)) {
            viewModel.setState {
                copy(isLoadingMoreUsers = true)
            }
            page++
            loadUsersList(
                viewModel = viewModel,
                getUsersListUseCase = getUsersListUseCase,
                currentState = viewModel.currentState
                )
        }
    }

    override fun cancelJob() {
        job?.cancel()
    }
}