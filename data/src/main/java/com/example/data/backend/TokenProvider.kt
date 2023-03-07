package com.example.data.backend

interface TokenProvider {
    fun getToken(): String
    fun refreshToken(): String
}