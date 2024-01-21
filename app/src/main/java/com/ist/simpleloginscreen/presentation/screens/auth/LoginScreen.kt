package com.ist.loginscreen.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
//import com.ist.loginscreen.components.ButtonComponent
//import com.ist.loginscreen.components.GooGle
//import com.ist.loginscreen.components.HeadingTextComponent
//import com.ist.loginscreen.components.MyLogo
//import com.ist.loginscreen.components.Or
//import com.ist.loginscreen.components.PasswordTextFieldComponent
//import com.ist.loginscreen.components.SignUp
//import com.ist.loginscreen.components.SimpleTextComponent
//import com.ist.loginscreen.components.UserFieldComponent
import com.ist.simpleloginscreen.components.ButtonComponent
import com.ist.simpleloginscreen.components.GooGle
import com.ist.simpleloginscreen.components.HeadingTextComponent
import com.ist.simpleloginscreen.components.MyLogo
import com.ist.simpleloginscreen.components.Or
import com.ist.simpleloginscreen.components.PasswordTextFieldComponent
import com.ist.simpleloginscreen.components.SignUp
import com.ist.simpleloginscreen.components.SimpleTextComponent
import com.ist.simpleloginscreen.components.UserFieldComponent
import com.ist.simpleloginscreen.presentation.MainViewModel
import com.ist.loginscreen.screens.login.Preview as Preview1

@Composable

@Preview1
fun LoginScreen(navController: NavHostController, vm: MainViewModel) {
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
            MyLogo()
            HeadingTextComponent(value = "Hi, Welcome back")
            SimpleTextComponent(value = "Order from us")
            UserFieldComponent(
                labelValue = "Email Address",
                icon = Icons.Default.Email,
                value = emailState.value.text,
                onValueChange = { emailState.value = it },
            )

            PasswordTextFieldComponent(
                labelValue = "Password",
                icon = Icons.Default.Lock,
                value = passState.value.text,
                onValueChange = { passState.value = it },
//                visualTransformation = PasswordVisualTransformation()
            )
            RememberMeRow()
//            ButtonComponent(value = "Login")
            Button(
                onClick = {
                    vm.onLogin(
                        emailState.value.text,
                        passState.value.text
                    )
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "LOGIN")
            }
            Or()
            GooGle()
            SignUp(navController)

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






