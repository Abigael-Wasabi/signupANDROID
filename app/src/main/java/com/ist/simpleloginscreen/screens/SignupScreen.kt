package com.ist.loginscreen.screens.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ist.loginscreen.components.ButtonComponent
import com.ist.loginscreen.components.GitS
import com.ist.loginscreen.components.HeadingTextComponent
import com.ist.loginscreen.components.LoginText
import com.ist.loginscreen.components.MyProfile
import com.ist.loginscreen.components.OrWith
import com.ist.loginscreen.components.PasswordTextFieldComponent
import com.ist.loginscreen.components.SimpleTextComponent
import com.ist.loginscreen.components.UserFieldComponent
import androidx.compose.ui.tooling.preview.Preview as Preview1

@Composable //defines a UI component
@Preview1 //displays a preview of the comps in Andr Stud

fun SignupScreen(navController: NavHostController) {
    Surface(//building block that wraps other comps
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(50.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            MyProfile()
            HeadingTextComponent(value = "Create an account")
            SimpleTextComponent(value = "Connect with your friends today")
            UserFieldComponent(labelValue ="Username", icon = Icons.Default.Person)
            UserFieldComponent(labelValue ="Email Address", icon = Icons.Default.Email)
            UserFieldComponent(labelValue ="Phone Number", icon = Icons.Default.Phone)
            PasswordTextFieldComponent(labelValue = "Password", icon = Icons.Default.Lock )
            RememberMe()
            ButtonComponent(value = "Sign up")
            OrWith()
            GitS()
            LoginText()
        }
    }
}




@Composable
fun RememberMe() {
    Row(modifier = Modifier.padding(16.dp)) {
        Checkbox(checked = false, onCheckedChange = {})
        Spacer(modifier = Modifier.weight(1f))//space btwn checkbox n fp
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








