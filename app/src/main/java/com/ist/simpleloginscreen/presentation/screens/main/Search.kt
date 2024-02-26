//package com.ist.simpleloginscreen.presentation.screens.main
//
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.navigation.NavController
//import com.ist.simpleloginscreen.presentation.MainViewModel
//
//@Composable
//fun SearchScreen(navController: NavController, vm: MainViewModel) {
//    var isSearchVisible by remember { mutableStateOf(false) }
//    var searchText by remember { mutableStateOf("") }
//
//    Text(text = "Search Screen")
//    BottomNavigationMenu(
//        selectedItem = BottomNavigationItem.SEARCH,
//        navController = navController,
//        isSearchVisible = isSearchVisible,
//        onSearchTextChanged = { text -> searchText = text },
//        onSearchIconClicked = { isSearchVisible = !isSearchVisible }
//    )
//}
//
//
//
//
