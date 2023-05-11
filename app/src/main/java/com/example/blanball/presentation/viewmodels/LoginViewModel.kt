package com.example.blanball.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.results.LoginResultEntity
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
}