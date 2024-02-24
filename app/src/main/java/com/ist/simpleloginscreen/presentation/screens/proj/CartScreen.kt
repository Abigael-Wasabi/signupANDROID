package com.ist.simpleloginscreen.presentation.screens.proj

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ist.simpleloginscreen.presentation.MainViewModel
import com.ist.simpleloginscreen.presentation.screens.main.BottomNavigationItem
import com.ist.simpleloginscreen.presentation.screens.main.BottomNavigationMenu

fun verticalScroll(rememberScrollState: ScrollState, function: () -> Unit) = Unit

@Composable
fun CartScreen(navController: NavController, vm: MainViewModel, selectedItems: List<Item>) {
    var isSearchVisible by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    Column {
        BottomNavigationMenu(
            selectedItem = BottomNavigationItem.CART,
            navController = navController,
            isSearchVisible = isSearchVisible,
            onSearchTextChanged = { text -> searchText = text },
            onSearchIconClicked = { isSearchVisible = !isSearchVisible }
        )

        // Calculate total amount
        val totalAmount = selectedItems.sumOf { it.price }

        // State variable to track if phone number is filled
        var phoneNumberFilled by remember { mutableStateOf(false) }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Back",
                modifier = Modifier.clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputField(label = "Phone Number") { filled -> phoneNumberFilled = filled }
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Selected Items:")
            Spacer(modifier = Modifier.height(8.dp))

            // Display the selected items in a LazyColumnRow with scrollbar
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(selectedItems) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(item.imageUrl),
                            contentDescription = null,
                            modifier = Modifier.size(64.dp)
                        )
                        Column(
                            modifier = Modifier.padding(start = 16.dp)
                        ) {
                            Text(text = item.name)
                            Text(text = "$${item.price}")
                        }
                        Button(
                            onClick = {
                                vm.removeFromCart(item.name)
//                            calculateTotalAmount() -=item.price,
                            },
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Text(text = "Remove from cart")
                        }
                    }
                }
            }
            //scrollbar to the LazyColumn
            verticalScroll(rememberScrollState()) {
                // Empty composable used for scrolling
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Total Amount: \$${"%.2f".format(totalAmount)}")
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* Pay button action */ },
                enabled = phoneNumberFilled
            ) {
                Text(text = "Pay")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* Order button action */ },
                enabled = phoneNumberFilled
            ) {
                Text(text = "Order")
            }
        }
    }
}

// Model class for representing an item
data class Item(val name: String, val price: Double, val imageUrl: String)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(label: String, onFilled: (Boolean) -> Unit) {
    var text by remember { mutableStateOf(TextFieldValue()) }
    Column {
        Text(text = label)
        TextField(
            value = text,
            onValueChange = {
                val isValidPhoneNumber = validatePhoneNumber(it.text)
                text = it
                onFilled(isValidPhoneNumber && it.text.isNotEmpty())
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

//valid phone no
fun validatePhoneNumber(phoneNumber: String): Boolean {
    //at least 10 chars
    if (phoneNumber.length < 10) return false

    //contain only nos and the + sign
    return phoneNumber.all { it.isDigit() || it == '+' }
}

fun calculateTotalAmount(items: List<Item>): Double {
    return items.sumOf { it.price }
}