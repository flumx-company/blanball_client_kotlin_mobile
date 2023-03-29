package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.*
import com.example.domain.entity.EmailResetResultEntity
import com.example.domain.repository.AppRepository
import kotlinx.coroutines.launch

class ResetPasswordStep1ViewModel(private val appRepository: AppRepository): ViewModel() {

    private val _requestResetResult = MutableLiveData<EmailResetResultEntity>()
    val requestResetResult: LiveData<EmailResetResultEntity> = _requestResetResult

    fun requestReset(email: String) {
        viewModelScope.launch {
            val result = appRepository.sendEmailPassReset(email)
            _requestResetResult.value = result
        }
    }


    class ResetPasswordStep1ViewModelFactory(
        val appRepository: AppRepository,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ResetPasswordStep1ViewModel(
                appRepository = appRepository) as T
        }
    }
}