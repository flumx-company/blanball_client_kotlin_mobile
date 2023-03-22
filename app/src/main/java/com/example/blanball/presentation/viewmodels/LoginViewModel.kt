package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.*
import com.example.domain.entity.LoginResultEntity
import com.example.domain.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {


    private val _loginResult = MutableLiveData<LoginResultEntity>()
    val loginResult: LiveData<LoginResultEntity> = _loginResult

        fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = loginRepository.login(email, password)
            _loginResult.value = result
        }
    }

    class LoginViewModelFactory(
        val loginRepository: LoginRepository,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginViewModel(
                loginRepository = loginRepository
            ) as T
        }
    }
}