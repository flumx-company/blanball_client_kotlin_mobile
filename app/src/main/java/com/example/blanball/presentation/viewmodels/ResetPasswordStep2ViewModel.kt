package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.*
import com.example.domain.entity.EmailResetResultEntity
import com.example.domain.entity.SendCodeResultEntity
import com.example.domain.repository.AppRepository
import kotlinx.coroutines.launch

class ResetPasswordStep2ViewModel(private val appRepository: AppRepository) : ViewModel() {

    private val _requestResetResult = MutableLiveData<EmailResetResultEntity>()
    val requestResetResult: LiveData<EmailResetResultEntity> = _requestResetResult

    private val _sendCodeResult = MutableLiveData<SendCodeResultEntity>()
    val sendCodeResult: LiveData<SendCodeResultEntity> = _sendCodeResult

    fun requestReset(email: String) {
        viewModelScope.launch {
            val result = appRepository.sendEmailPassReset(email)
            _requestResetResult.value = result
        }
    }

    fun sendCode(code: String) {
        viewModelScope.launch {
            val result = appRepository.sendCode(code)
            _sendCodeResult.value = result
        }
    }

    class ResetPasswordStep2ViewModelFactory(
        val appRepository: AppRepository,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ResetPasswordStep2ViewModel(
                appRepository = appRepository
            ) as T
        }
    }
}