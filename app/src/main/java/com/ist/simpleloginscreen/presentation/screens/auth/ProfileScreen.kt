package com.ist.simpleloginscreen.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ist.simpleloginscreen.components.ButtonComponent
import com.ist.simpleloginscreen.components.MyProfile
import com.ist.simpleloginscreen.components.PasswordTextFieldComponent
import com.ist.simpleloginscreen.components.UserFieldComponent
import com.ist.simpleloginscreen.presentation.MainViewModel
import androidx.compose.ui.tooling.preview.Preview as Preview1


@Composable //defines a UI component
@Preview1 //displays a preview of the comps in Andr Stud

fun Profile(navController: NavHostController, vm: MainViewModel) {
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
            val usernameState = remember { mutableStateOf(TextFieldValue()) }
            val emailState = remember { mutableStateOf(TextFieldValue()) }
            val passState = remember { mutableStateOf(TextFieldValue()) }
            MyProfile()
            UserFieldComponent(
                labelValue = "Username",
                icon = Icons.Default.Person,
                value = usernameState.value.text,
            ) { usernameState.value = it }

            UserFieldComponent(
                labelValue = "Email Address",
                icon = Icons.Default.Email,
                value = emailState.value.text,
            ) { emailState.value = it }

            PasswordTextFieldComponent(
                labelValue = "Password",
                icon = Icons.Default.Lock,
                value = passState.value.text,
            ) { passState.value = it }

//            UserFieldComponent(labelValue = "Phone Number", icon = Icons.Default.Phone)

            Button(
                onClick = {
                    vm.onSignup(
                        usernameState.value.text,
                        emailState.value.text,
                        passState.value.text
                    )
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "SIGN UP")
            }


            Spacer(modifier = Modifier.height(16.dp))

            ButtonComponent(value = "Update profile")
        }
    }
}


