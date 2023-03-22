package com.example.domain.events

class AuthEvent(private val isAuthenticated: Boolean) {
    fun isAuthenticated(): Boolean {
        return isAuthenticated
    }
}