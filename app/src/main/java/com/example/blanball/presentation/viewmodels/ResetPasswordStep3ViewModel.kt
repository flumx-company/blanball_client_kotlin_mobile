package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.*
import com.example.domain.entity.ResetCompleteResultEntity
import com.example.domain.repository.AppRepository
import kotlinx.coroutines.launch

class ResetPasswordStep3ViewModel(private val appRepository: AppRepository) : ViewModel() {

    private val _requestResetCompleteResult = MutableLiveData<ResetCompleteResultEntity>()
    val requestResetCompleteResult: LiveData<ResetCompleteResultEntity> = _requestResetCompleteResult

    fun requestCompleteReset(newPassword: String) {
        viewModelScope.launch {
            val result = appRepository.changePassword(newPassword)
            _requestResetCompleteResult.value = result
        }
    }


    class ResetPasswordStep3ViewModelFactory(
        val appRepository: AppRepository,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ResetPasswordStep3ViewModel(
                appRepository = appRepository
            ) as T
        }
    }
}
