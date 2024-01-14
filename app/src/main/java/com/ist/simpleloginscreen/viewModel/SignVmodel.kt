package com.ist.simpleloginscreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ist.simpleloginscreen.model.Roles
import com.ist.simpleloginscreen.model.User
import com.ist.simpleloginscreen.repo.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    fun registerUser(email: String, password: String, username: String, phone: Int?) {
        viewModelScope.launch {
            //create new user
            val newUser = User(
                id = "",
                email = email,
                password = password,
                username = username,
                phone = phone,
                roles = listOf(Roles.customer)
            )

            // Call func from the UserRepository
            val registeredUser = userRepository.registerUser(newUser)

            //welcome or navigate
            if (registeredUser != null) {
                println("welcome")
            } else {
                println("error")
            }
        }
    }
}

annotation class HiltViewModel

