package com.ist.loginscreen.presentation.screens.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ist.simpleloginscreen.presentation.MainViewModel
import com.ist.simpleloginscreen.presentation.screens.main.BottomNavigationItem
import com.ist.simpleloginscreen.presentation.screens.main.BottomNavigationMenu

@Composable
fun MyServicesScreen(navController: NavController, vm: MainViewModel) {
    val userData = vm.userData.value
    val isLoading = vm.inProgress.value
    BottomNavigationMenu(
        selectedItem = BottomNavigationItem.SERVICES,
        navController = navController
    )
}