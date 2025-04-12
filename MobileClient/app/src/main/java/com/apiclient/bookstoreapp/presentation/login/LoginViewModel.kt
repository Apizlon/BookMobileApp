package com.apiclient.bookstoreapp.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apiclient.bookstoreapp.domain.usecase.LoginUseCase

class LoginViewModel : ViewModel() {

    private val loginUseCase = LoginUseCase()

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun login(username: String, password: String) {
        if (loginUseCase.execute(username, password)) {
            _loginSuccess.value = true
            _error.value = null
        } else {
            _loginSuccess.value = false
            _error.value = "Неверный логин или пароль"
        }
    }
}
