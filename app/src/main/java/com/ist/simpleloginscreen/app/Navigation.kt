package com.ist.simpleloginscreen.app

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ist.loginscreen.presentation.screens.main.MyServicesScreen
import com.ist.loginscreen.screens.login.LoginScreen
import com.ist.loginscreen.screens.signup.SignupScreen
import com.ist.ondemand.presentation.screens.main.ServiceScreen
import com.ist.simpleloginscreen.presentation.MainViewModel
import com.ist.simpleloginscreen.presentation.common.NotificationMessage
import com.ist.simpleloginscreen.presentation.screens.auth.ProfileScreen
import com.ist.simpleloginscreen.presentation.screens.main.SearchScreen
import com.ist.simpleloginscreen.presentation.screens.proj.CartScreen


@Composable

fun SimpleLoginApp() {
    val vm: MainViewModel = hiltViewModel()
    val navController = rememberNavController()
    val selectedItems = vm.getSelectedItems()
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
        composable(Routes.Search.route) {
            SearchScreen(navController = navController, vm = vm)
        }
        composable(Routes.MyServices.route) {
            MyServicesScreen(navController = navController, vm = vm)
        }
        composable(Routes.Profile.route) {
            ProfileScreen(navController = navController, vm = vm)
        }
        composable(Routes.Cart.route) {
            CartScreen(navController = navController, vm = vm, selectedItems = selectedItems)
        }
//        navController.navigate(route = "cart") {
//            launchSingleTop = true
//            restoreState = true
//            popUpTo("products") {
//                inclusive = true
//            }
//            // Passing selected items to CartScreen
//            CartScreen(navController = navController, vm = vm, selectedItems = selectedItems)
//        }
    }
}
