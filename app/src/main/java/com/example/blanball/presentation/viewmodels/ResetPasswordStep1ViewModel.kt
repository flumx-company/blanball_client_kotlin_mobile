package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.*
import com.example.domain.entity.RequestResetResultEntity
import com.example.domain.repository.AppRepository
import kotlinx.coroutines.launch

class ResetPasswordStep1ViewModel(private val appRepository: AppRepository): ViewModel() {

    private val _requestResetResult = MutableLiveData<RequestResetResultEntity>()
    val requestResetResult: LiveData<RequestResetResultEntity> = _requestResetResult

    fun requestReset(email: String) {
        viewModelScope.launch {
            val result = appRepository.requestReset(email)
            _requestResetResult.value = result
        }
    }


    class LoginViewModelFactory(
        val appRepository: AppRepository,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginViewModel(
                appRepository = appRepository) as T
        }
    }
}