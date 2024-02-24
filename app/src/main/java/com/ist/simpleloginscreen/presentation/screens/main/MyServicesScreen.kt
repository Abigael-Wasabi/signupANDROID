package com.ist.loginscreen.presentation.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.ist.simpleloginscreen.presentation.MainViewModel
import com.ist.simpleloginscreen.presentation.screens.main.BottomNavigationItem
import com.ist.simpleloginscreen.presentation.screens.main.BottomNavigationMenu

@Composable
fun MyServicesScreen(navController: NavController, vm: MainViewModel) {
    val userData = vm.userData.value
    val isLoading = vm.inProgress.value
    var isSearchVisible by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    BottomNavigationMenu(
        selectedItem = BottomNavigationItem.SERVICES,
        navController = navController,
        isSearchVisible = isSearchVisible,
        onSearchTextChanged = { text -> searchText = text },
        onSearchIconClicked = { isSearchVisible = !isSearchVisible }
    )
}


