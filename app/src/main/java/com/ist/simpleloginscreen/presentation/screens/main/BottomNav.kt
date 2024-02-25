package com.ist.simpleloginscreen.presentation.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ist.simpleloginscreen.R
import com.ist.simpleloginscreen.app.Routes
import com.ist.simpleloginscreen.presentation.common.navigateTo
import com.ist.simpleloginscreen.presentation.ui.theme.BurntOrange


enum class BottomNavigationItem(val icon: Int, val navDestination: Routes? = null) {
    SERVICES(R.drawable.ic_home, Routes.Services),
    SEARCH(R.drawable.ic_search),
    CART(R.drawable.ic_cart, Routes.Cart),
    PROFILE(R.drawable.ic_user, Routes.Profile),
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationMenu(
    selectedItem: BottomNavigationItem,
    navController: NavController,
    isSearchVisible: Boolean,
    onSearchTextChanged: (String) -> Unit,
    onSearchIconClicked: () -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 4.dp)
                .background(Color.White)
        ) {

            for (item in BottomNavigationItem.values()) {
                val isSelected = item == selectedItem
                Image(
                    painter = painterResource(id = item.icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(5.dp)
                        .weight(1f)
                        .clickable {
                            if (item.navDestination != null) {
                                navigateTo(navController, item.navDestination)
                            } else {
                                onSearchIconClicked()
                            }
                        },
                    colorFilter = if (isSelected) ColorFilter.tint(BurntOrange)
                    else ColorFilter.tint(Color.Gray)
                )
            }
        }

        // Display search input field if isSearchVisible is true
        if (isSearchVisible) {
            TextField(
                value = searchText,
                onValueChange = { text -> onSearchTextChanged(text) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Search for products...") }
            )
        }
    }
}