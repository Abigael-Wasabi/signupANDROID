package com.ist.simpleloginscreen.presentation.screens.auth

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ist.simpleloginscreen.app.Routes
import com.ist.simpleloginscreen.presentation.MainViewModel
import com.ist.simpleloginscreen.presentation.common.CommonImage
import com.ist.simpleloginscreen.presentation.common.ProgressSpinner
import com.ist.simpleloginscreen.presentation.common.navigateTo
import com.ist.simpleloginscreen.presentation.screens.main.BottomNavigationItem
import com.ist.simpleloginscreen.presentation.screens.main.BottomNavigationMenu


/**Composable function that represents the profile screen.*/
//navController The navigation controller used for navigating between screens.
//vm Instance of the main view model

@Composable
fun ProfileScreen(navController: NavController, vm: MainViewModel) {
    var isSearchVisible by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    Column {
        BottomNavigationMenu(
            selectedItem = BottomNavigationItem.PROFILE,
            navController = navController,
            isSearchVisible = isSearchVisible,
            onSearchTextChanged = { text -> searchText = text },
            onSearchIconClicked = { isSearchVisible = !isSearchVisible }
        )

        // Check if data is being loaded
        val isLoading = vm.inProgress.value
        if (isLoading) {
            ProgressSpinner()
        } else {
            // Retrieve user data
            val userData = vm.userData.value

            // Initialize mutable state variables for name, username, and bio
            var name by rememberSaveable { mutableStateOf(userData?.name ?: "") }
            var username by rememberSaveable { mutableStateOf(userData?.username ?: "") }
            var bio by rememberSaveable { mutableStateOf(userData?.bio ?: "") }

            // Render the profile content
            ProfileContent(vm = vm,
                name = name,
                username = username,
                bio = bio,
                onNameChange = { name = it },
                onUsernameChange = { username = it },
                onBioChange = { bio = it },
                onSave = { vm.updateProfileData(name, username, bio) },
                onBack = { navigateTo(navController = navController, Routes.Services) },
                onLogout = {
                    vm.onLogout()
                    navigateTo(navController, Routes.Login)
                })
        }
    }
}


//vm The view model for the screen.
//name The name of the user.
//username The username of the user.
//bio The bio of the user.
// onNameChange Callback function for name changes.
//onUsernameChange Callback function for username changes.
// onBioChange Callback function for bio changes.
// onSave Callback function for saving changes.
// onBack Callback function for navigating back.
// onLogout Callback function for logging out.


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    vm: MainViewModel,
    name: String,
    username: String,
    bio: String,
    onNameChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onBioChange: (String) -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit,
    onLogout: () -> Unit
) {
    val scrollState = rememberScrollState()
    val imageUrl = vm.userData.value?.imageUrl

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Back", modifier = Modifier.clickable { onBack.invoke() })
            Text(text = "Save", modifier = Modifier.clickable { onSave.invoke() })
        }



        ProfileImage(imageUrl = imageUrl, vm = vm)



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Name", modifier = Modifier.width(100.dp))
            TextField(
                value = name,
                onValueChange = onNameChange,

                )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Username", modifier = Modifier.width(100.dp))
            TextField(
                value = username,
                onValueChange = onUsernameChange,
                colors = TextFieldDefaults.textFieldColors(

                    textColor = Color.Black
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(text = "Bio", modifier = Modifier.width(100.dp))
            TextField(
                value = bio,
                onValueChange = onBioChange,
                colors = TextFieldDefaults.textFieldColors(

                    textColor = Color.Black
                ),
                singleLine = false,
                modifier = Modifier.height(150.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Logout", modifier = Modifier.clickable { onLogout.invoke() })
        }
    }
}

/**Composable function that displays the profile image and allows the user to change it.*/
//@param imageUrl The URL of the profile image.


@Composable
fun ProfileImage(imageUrl: String?, vm: MainViewModel) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { vm.uploadProfileImage(uri) }
    }

    Box(modifier = Modifier.height(IntrinsicSize.Min)) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable { launcher.launch("image/*") },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .padding(8.dp)
                    .size(100.dp)
            ) {
                CommonImage(data = imageUrl)
            }
            Text(text = "Change profile picture")
        }

        val isLoading = vm.inProgress.value
        if (isLoading)
            ProgressSpinner()
    }
}



