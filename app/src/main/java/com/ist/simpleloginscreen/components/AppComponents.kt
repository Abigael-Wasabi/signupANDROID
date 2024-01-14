@file:OptIn(ExperimentalMaterial3Api::class)

package com.ist.loginscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import com.ist.loginscreen.R
//import com.ist.loginscreen.ui.theme.BgColor
//import com.ist.loginscreen.ui.theme.Primary
//import com.ist.loginscreen.ui.theme.*
import com.ist.simpleloginscreen.R
import com.ist.simpleloginscreen.ui.theme.BgColor
import com.ist.simpleloginscreen.ui.theme.Primary


@Composable
fun SimpleTextComponent(value:String){
    Text(text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp),
        style = TextStyle(
            fontSize =14.sp,
            fontWeight = FontWeight.Normal,
        ),
        textAlign = TextAlign.Center)

}
@Composable
fun HeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        textAlign = TextAlign.Center
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFieldComponent(
    labelValue: String,
    icon: ImageVector,
) {

    val textValue = remember {
        mutableStateOf("")
    }


    TextField(
        modifier = Modifier
            .fillMaxWidth(),

        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            containerColor = BgColor
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        leadingIcon = {
            Icon(icon, contentDescription = "")
        },
        onValueChange = {
            textValue.value = it
        },
    )
}
@Composable
fun PasswordTextFieldComponent(
    labelValue: String,
    icon: ImageVector,
) {

    val localFocusManager = LocalFocusManager.current
    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),

        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            containerColor = BgColor
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        maxLines = 1,
        value = password.value,
        onValueChange = {
            password.value = it
        },
        leadingIcon = {
            Icon(icon, contentDescription = "")
        },


        )
}
@Composable
fun ButtonComponent(value:String){
    Button(onClick = {  },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)

    ) {
        Text(text = value)
    }
}



@Composable
fun MyProfile(){

    Column (modifier = Modifier
        .padding(16.dp)
        .background(Color.Blue)
    ){

        Image(
            painter = painterResource(id = R.drawable.chess),
            contentDescription = "alt",
            modifier= Modifier
                .size(128.dp)
                .clip(CircleShape)
        )
    }
}




@Composable
fun SignUp(){
    Row(modifier = Modifier.padding(16.dp)){
        Text(
            text = "Dont have an account? Sign Up",
            modifier = Modifier.padding(end = 5.dp),
        )
    }
}

@Composable
fun LoginText(){
    Row(modifier = Modifier.padding(16.dp)){
        Text(
            text = "Already have an account? Login",
            modifier = Modifier.padding(end = 5.dp),
        )
    }
}

@Composable
fun OrWith(){
    Row(modifier = Modifier.padding(16.dp)){
        Text(
            text = "or With",
            modifier = Modifier.padding(end = 55.dp),
        )
    }
}


@Composable
fun GitS() {
    Row(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = {  },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.gith),
                contentDescription = "GitHub Icon",
                modifier = Modifier.size(24.dp)
            )
            Text(text = "GitHub")
        }

        Spacer(modifier = Modifier.width(16.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.gitl),
                contentDescription = "GitHub Icon",
                modifier = Modifier.size(24.dp)
            )
            Text(text = "GitLab")
        }
    }
}

@Composable
fun AdOptions(value: String) {
    Column (modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(8.dp)
        .background(Color.Gray)
        .clickable { }
    ){

        Text(text = value)

    }

}

