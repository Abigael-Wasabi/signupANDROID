package com.ist.loginscreen.screens.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import com.ist.simpleloginscreen.components.GooGle
import com.ist.simpleloginscreen.components.HeadingTextComponent
import com.ist.simpleloginscreen.components.LoginText
import com.ist.simpleloginscreen.components.MyLogo
import com.ist.simpleloginscreen.components.Or
import com.ist.simpleloginscreen.components.PasswordTextFieldComponent
import com.ist.simpleloginscreen.components.SimpleTextComponent
import com.ist.simpleloginscreen.components.UserFieldComponent
import com.ist.simpleloginscreen.presentation.MainViewModel
import androidx.compose.ui.tooling.preview.Preview as Preview1

@Composable //defines a UI component
@Preview1 //displays a preview of the comps in Andr Stud

fun SignupScreen(navController: NavHostController, vm: MainViewModel) {
    Surface(//building block that wraps other comps
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(50.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val usernameState = remember { mutableStateOf(TextFieldValue()) }
            val emailState = remember { mutableStateOf(TextFieldValue()) }
            val passState = remember { mutableStateOf(TextFieldValue()) }
            MyLogo()
            HeadingTextComponent(value = "Create an account")
            SimpleTextComponent(value = "Order from us")
            UserFieldComponent(
                labelValue = "Username",
                icon = Icons.Default.Person,
                value = usernameState.value.text,
                onValueChange = { usernameState.value = it },
            )

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
            )

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
            Or()
            LoginText(navController)
            GooGle()
        }
    }
}


//@Composable
//fun SignUpButton(value:String){
//    val usernameState = remember { mutableStateOf(TextFieldValue()) }
//    val emailState = remember { mutableStateOf(TextFieldValue()) }
//    val passState = remember { mutableStateOf(TextFieldValue()) }
//    Button(
//        onClick = {
//            vm.onSignUp(
//                usernameState.value.text,
//                emailState.value.text,
//                passState.value.text
//            )
//        },
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(50.dp)
//
//    ) {
//        Text(text = value)
//    }
//}

