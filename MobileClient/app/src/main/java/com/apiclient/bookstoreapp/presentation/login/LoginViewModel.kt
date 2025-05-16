package com.apiclient.bookstoreapp.presentation.login

import androidx.lifecycle.ViewModel
import com.apiclient.bookstoreapp.domain.usecase.LoginUseCase

class LoginViewModel : ViewModel() {

    private val loginUseCase = LoginUseCase()

    fun validateLogin(username: String, password: String): Boolean {
        return loginUseCase.execute(username, password)
    }
}