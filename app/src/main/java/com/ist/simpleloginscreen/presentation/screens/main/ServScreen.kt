package com.ist.simpleloginscreen.presentation.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ist.simpleloginscreen.components.AdOptions
import com.ist.simpleloginscreen.components.ButtonComponent
import com.ist.simpleloginscreen.components.DropDown
import com.ist.simpleloginscreen.components.MyImage
import com.ist.simpleloginscreen.components.SimpleTextComponent
import com.ist.simpleloginscreen.R
import com.ist.simpleloginscreen.presentation.MainViewModel

@Composable //defines a UI component
@Preview //displays a preview of the comps in Andr Stud

fun ServiceScreen(navController: NavHostController, vm: MainViewModel) {
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
            MyImage()
            SimpleTextComponent(value = "Service ID")
            ButtonComponent(value = "Name")
            ButtonComponent(value = "Add to cart")
            AdOptions("Service provider")
            AdOptions("description")
            AdOptions("image")
        }
    }
}
