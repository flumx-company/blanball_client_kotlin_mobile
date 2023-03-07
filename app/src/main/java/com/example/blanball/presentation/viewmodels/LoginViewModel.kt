package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.LoginResultEntity
import com.example.domain.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {


    private val _loginResult = MutableLiveData<LoginResultEntity>()
    val loginResult: LiveData<LoginResultEntity> = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val result = loginRepository.login(username, password)
            _loginResult.value = result
        }
    }
}