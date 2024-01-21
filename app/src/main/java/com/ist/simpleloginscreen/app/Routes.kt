package com.ist.simpleloginscreen.app

sealed class Routes(val route: String) {
    object Profile : Routes("profile")
    object Signup : Routes("signup")
    object Login : Routes("login")
    object Services : Routes("services")
}