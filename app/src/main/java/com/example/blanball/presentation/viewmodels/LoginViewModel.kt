package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.*
import com.example.domain.entity.LoginResultEntity
import com.example.domain.repository.AppRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val appRepository: AppRepository) : ViewModel() {


    private val _loginResult = MutableLiveData<LoginResultEntity>()
    val loginResult: LiveData<LoginResultEntity> = _loginResult

        fun login(email: String, password: String) {
        viewModelScope.launch {  //dispatcher io
            val result = appRepository.login(email, password)
            _loginResult.value = result
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