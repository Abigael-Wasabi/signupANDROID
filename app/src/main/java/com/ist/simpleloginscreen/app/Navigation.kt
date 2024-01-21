package com.ist.simpleloginscreen.app

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ist.loginscreen.screens.login.LoginScreen
import com.ist.loginscreen.screens.signup.SignupScreen
import com.ist.simpleloginscreen.presentation.MainViewModel
import com.ist.simpleloginscreen.presentation.common.NotificationMessage
import com.ist.simpleloginscreen.presentation.screens.main.ServiceScreen
import com.ist.simpleloginscreen.presentation.screens.auth.Profile


@Composable
fun SimpleLoginApp() {
    val vm: MainViewModel = hiltViewModel()
    val navController = rememberNavController()
    NotificationMessage(vm = vm)
    NavHost(navController = navController, startDestination = Routes.Signup.route) {

        composable(Routes.Signup.route) {
            SignupScreen(navController = navController, vm = vm)
        }
        composable(Routes.Login.route) {
            LoginScreen(navController = navController, vm = vm)
        }
        composable(Routes.Services.route) {
            ServiceScreen(navController = navController, vm = vm)
        }
        composable(Routes.Profile.route) {
            Profile(navController = navController, vm = vm)
        }
    }
}

