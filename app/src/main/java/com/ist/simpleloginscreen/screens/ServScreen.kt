package com.ist.simpleloginscreen.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ist.loginscreen.components.AdOptions
import com.ist.loginscreen.components.ButtonComponent
import com.ist.loginscreen.components.MyProfile
import com.ist.loginscreen.components.SimpleTextComponent

@Composable //defines a UI component
//@Preview //displays a preview of the comps in Andr Stud

fun ServiceScreen(navController: NavHostController) {
    Surface(//building block that wraps other comps
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(50.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyProfile()
            SimpleTextComponent(value = "Design concept")
            ButtonComponent(value = "Our Package")
            ButtonComponent(value = "Add to cart")
            AdOptions("Logo Design")
            AdOptions("Brand Identity Design")
            AdOptions("Letterheads")
            AdOptions("Business Cards Design")
        }
    }
}


