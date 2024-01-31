package com.ist.simpleloginscreen.presentation.screens.proj

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ist.simpleloginscreen.presentation.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController, vm: MainViewModel) {
    var location by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var totalAmount by remember { mutableStateOf(0.0) }
    val goodsInCart = listOf("Item 1", "Item 2", "Item 3") // Example list of goods in cart

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Location selection
        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Phone number input
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Goods ordered
        LazyColumn {
            items(goodsInCart) { item ->
                Text(text = item)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Total amount calculated
        Text(text = "Total Amount: $$totalAmount")

        Spacer(modifier = Modifier.height(16.dp))

        // Pay button
        Button(
            onClick = { /* Handle payment */ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Pay")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Order button
        Button(
            onClick = { /* Handle order placement */ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Place Order")
        }
    }
}
