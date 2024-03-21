package com.example.blanball.utils.workers

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.blanball.presentation.data.RatingUsersMainContract
import com.example.blanball.presentation.viewmodels.UsersRatingViewModel
import com.example.domain.entity.results.GetUsersListResult
import com.example.domain.usecases.interfaces.GetUsersListUseCase
import com.example.domain.utils.Integers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoadUsersWorkerImpl @Inject constructor (
) : LoadUsersWorker {

    private var job: Job? = null
    private var page = Integers.ONE

   override fun resetPage() {
        page = Integers.ONE
    }

  override fun loadUsersList(
        viewModel: UsersRatingViewModel,
        getUsersListUseCase: GetUsersListUseCase,
        currentState: RatingUsersMainContract.State,
        gender: String?,
        ageMin: Int,
        ageMax: Int,
        ordering: String?,
        position: String?,
        ) {
       job = viewModel.viewModelScope.launch(Dispatchers.IO) {
            when (val result = getUsersListUseCase.executeGetUsersList(
                page = page,
                gender = gender,
                age_min = ageMin,
                age_max = ageMax,
                ordering = ordering,
                position = position,
            )) {
                is GetUsersListResult.Success -> {
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
                is GetUsersListResult.Error -> {
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
        currentState: RatingUsersMainContract.State,
        gender: String?,
        ageMin: Int,
        ageMax: Int,
        ordering: String?,
        position: String?,
        ) {
        if (!(currentState.isLoadingMoreUsers || currentState.allUsersLoaded)) {
            viewModel.setState {
                copy(isLoadingMoreUsers = true)
            }
            page++
            loadUsersList(
                viewModel = viewModel,
                getUsersListUseCase = getUsersListUseCase,
                currentState = viewModel.currentState,
                gender = gender,
                ageMin = ageMin,
                ageMax = ageMax,
                ordering = ordering,
                position = position
                )
        }
    }

    override fun cancelJob() {
        job?.cancel()
    }
}