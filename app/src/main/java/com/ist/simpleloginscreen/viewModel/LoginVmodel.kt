package com.ist.simpleloginscreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ist.simpleloginscreen.repo.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            //!*call user func frm  UserRepository
            val user = userRepository.loginUser(email, password)

            //welcome or navigate to next screen
            if (user != null) {
                println("welcome")
            } else {
                println("user not found")
            }
        }
    }
}