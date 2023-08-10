package com.example.blanball.utils.workers

import com.example.blanball.presentation.data.RatingUsersMainContract
import com.example.blanball.presentation.viewmodels.UsersRatingViewModel
import com.example.domain.usecases.interfaces.GetUsersListUseCase

interface LoadUsersWorker {
    fun resetPage()
    fun loadUsersList(
        viewModel: UsersRatingViewModel,
        getUsersListUseCase: GetUsersListUseCase,
        currentState: RatingUsersMainContract.State,
        gender: String?,
        ageMin: Int,
        ageMax: Int,
        ordering: String?,
        position: String?,
    )
    fun loadMoreUsers(
        viewModel: UsersRatingViewModel,
        getUsersListUseCase: GetUsersListUseCase,
        currentState: RatingUsersMainContract.State,
        gender: String?,
        ageMin: Int,
        ageMax: Int,
        ordering: String?,
        position: String?
    )
    fun cancelJob()
}