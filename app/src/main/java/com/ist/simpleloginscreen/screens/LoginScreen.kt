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
import com.ist.loginscreen.components.MyProfile
import com.ist.loginscreen.components.OrWith
import com.ist.loginscreen.components.PasswordTextFieldComponent
import com.ist.loginscreen.components.SignUp
import com.ist.loginscreen.components.SimpleTextComponent
import com.ist.loginscreen.components.UserFieldComponent
import com.ist.loginscreen.screens.login.Preview as Preview1

@Composable

@Preview1
fun LoginScreen(navController: NavHostController) {
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
            MyProfile()
            HeadingTextComponent(value = "Hi, Welcome back")
            SimpleTextComponent(value = "Hello again, you've been missed")
            UserFieldComponent(labelValue = "Email", icon = Icons.Default.Email)
            PasswordTextFieldComponent(labelValue = "Password", icon = Icons.Default.Lock)
            RememberMeRow()
            ButtonComponent(value = "Login")
            OrWith()
            GitS()
            SignUp()

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






