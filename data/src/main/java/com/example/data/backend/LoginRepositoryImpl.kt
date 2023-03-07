package com.example.data.backend

import com.example.data.backend.models.AuthRequest
import com.example.domain.entity.LoginErrorEntity
import com.example.domain.entity.LoginResultEntity
import com.example.domain.repository.LoginRepository

class LoginRepositoryImpl (private val apiService: ApiService) : LoginRepository  {

    override suspend fun login(username: String, password: String): LoginResultEntity {
        return try {

            val authRequest = AuthRequest(username, password)
            val authResponseDto = apiService.loginAuthorization(authRequest)

            val data = authResponseDto.data
            val error = authResponseDto.data.errors[0].detail
            LoginResultEntity.Success()
        } catch (e: Exception){
            LoginResultEntity.Error(LoginErrorEntity(error()))
        }



    }


}