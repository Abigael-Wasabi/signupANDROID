package com.ist.loginscreen.screens.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ist.simpleloginscreen.data.Event
import com.ist.simpleloginscreen.presentation.MainViewModel
import com.ist.simpleloginscreen.presentation.common.CheckSignedIn
import com.ist.simpleloginscreen.presentation.common.ProgressSpinner
import com.ist.simpleloginscreen.presentation.components.HeadingTextComponent
import com.ist.simpleloginscreen.presentation.components.MyLogo
import com.ist.simpleloginscreen.presentation.components.Or
import com.ist.simpleloginscreen.presentation.components.SignUp
import com.ist.simpleloginscreen.presentation.components.SimpleTextComponent
import com.ist.loginscreen.screens.login.Preview as Preview1

@OptIn(ExperimentalMaterial3Api::class)
@Composable

@Preview1
fun LoginScreen(navController: NavHostController, vm: MainViewModel) {
    CheckSignedIn(vm = vm, navController = navController)
    val focus = LocalFocusManager.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val emailState = remember { mutableStateOf(TextFieldValue()) }
            val passState = remember { mutableStateOf(TextFieldValue()) }
            val popupNotification = remember { mutableStateOf<Event<String>?>(null) }
            MyLogo()
            HeadingTextComponent(value = "Hi, Welcome back")
            SimpleTextComponent(value = "Order from us")
            OutlinedTextField(value = emailState.value,
                onValueChange = { emailState.value = it },
                modifier = Modifier.padding(8.dp),
                label = { Text(text = "Email") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                })

            OutlinedTextField(
                value = passState.value,
                onValueChange = { passState.value = it },
                modifier = Modifier.padding(8.dp),
                label = { Text(text = "Password") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                },
                visualTransformation = PasswordVisualTransformation()
            )
            RememberMeRow()
            Button(
                onClick = {
                    val email = emailState.value.text
                    val pass = passState.value.text
                    if (email.isNotEmpty() && pass.isNotEmpty()) {
                        vm.onLogin(
                            email,
                            pass,
                            navController = navController
                        )
                        navController.navigate("Services")
                    } else {
                        Log.d(null, "Please fill in all the fields")
                        popupNotification.value = Event("Please fill in all the fields")
                    }
                },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(text = "LOGIN")
            }
            Or()
            SignUp(navController)

            popupNotification.value?.getContentIfNotHandled()?.let { message ->
                Text(
                    text = message,
                    color = Color.Red,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            val isLoading = vm.inProgress.value
            if (isLoading) {
                ProgressSpinner()
            }

        }
    }
}

annotation class Preview


@Composable
fun RememberMeRow() {
    Row(modifier = Modifier.padding(16.dp)) {
        Checkbox(checked = false, onCheckedChange = {})
        androidx.compose.foundation.layout.Spacer(modifier = Modifier.weight(1f))//space btwn checkbox n fp
        Text(
            text = "Remember Me",
            modifier = Modifier.padding(end = 55.dp),
        )

        Text(
            text = "Forgot Password",
            modifier = Modifier.padding(end = 16.dp),
        )
    }
}




