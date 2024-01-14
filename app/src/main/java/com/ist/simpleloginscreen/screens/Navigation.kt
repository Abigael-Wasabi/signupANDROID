package com.ist.simpleloginscreen.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ist.loginscreen.screens.signup.SignupScreen

@Composable
@Preview
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "SignUp"
    ) {
        composable(route = "Login") {
            LoginScreen(navController)
        }
        composable(route = "SignUp") {
            SignupScreen(navController)
        }

    }
}

@Composable
fun LoginScreen(navController: NavHostController) {
    TODO("Not yet implemented")
}
