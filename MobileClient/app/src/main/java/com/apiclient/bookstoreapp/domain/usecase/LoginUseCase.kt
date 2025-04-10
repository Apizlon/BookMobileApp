package com.apiclient.bookstoreapp.domain.usecase

class LoginUseCase {
    fun execute(username: String, password: String): Boolean {

        // пример простой валидации
        return username.isNotBlank() && password.length >= 6
    }
}