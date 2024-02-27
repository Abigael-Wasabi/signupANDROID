package com.ist.simpleloginscreen.presentation.screens.proj

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ist.simpleloginscreen.presentation.MainViewModel
import com.ist.simpleloginscreen.presentation.components.OrderConfirmationDialog
import com.ist.simpleloginscreen.presentation.screens.main.BottomNavigationItem
import com.ist.simpleloginscreen.presentation.screens.main.BottomNavigationMenu

fun verticalScroll(rememberScrollState: ScrollState, function: () -> Unit) = Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController, vm: MainViewModel, selectedItems: List<Item>) {
    var isSearchVisible by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var deliveryTime by remember { mutableStateOf("") }
    var deliveryDate by remember { mutableStateOf("") }
    var isPhoneNumberValid by remember { mutableStateOf(false) }
    var isDateValid by remember { mutableStateOf(false) }
    var isTimeValid by remember { mutableStateOf(false) }
    var selectedLocation by remember { mutableStateOf("") }
    var isLocationValid by remember { mutableStateOf(false) }
    isLocationValid = selectedLocation in nairobiLocations
    var orderButtonClicked by remember { mutableStateOf(false) }
    var isPayButtonClicked by remember { mutableStateOf(false) }
//    var orderButtonEnabled by remember { mutableStateOf(false) }


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

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Back",
                modifier = Modifier.clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.height(16.dp))
            InputField(
                label = "Phone Number",
                value = phoneNumber,
                onValueChange = { value ->
                    phoneNumber = value
                    isPhoneNumberValid = validatePhoneNumber(value)
                },
                onFilled = { /* No action needed for phone number */ },
            )
            if (!isPhoneNumberValid) {
                Text(text = "Enter a valid phone number", color = Color.Red)
            }
            Spacer(modifier = Modifier.height(16.dp))

            InputField(
                label = "Location",
                value = selectedLocation,
                onValueChange = { value ->
                    selectedLocation = value
                },
                onFilled = { /* No action needed for location */ },
            )

            if (!isLocationValid) {
                Text(text = "location within Nairobi", color = Color.Red)
            }

            InputField(
                label = "Delivery Date (dd/MM/yyyy or dd.MM.yyyy)",
                value = deliveryDate,
                onValueChange = { value ->
                    deliveryDate = value
                    isDateValid = validateDate(value)
                },
                onFilled = { /* No action needed for delivery date */ },
            )
            if (!isDateValid) {
                Text(text = "Enter a valid date format", color = Color.Red)
            }
            Spacer(modifier = Modifier.height(16.dp))

            InputField(
                label = "Delivery Time (HH:mm)",
                value = deliveryTime,
                onValueChange = { value ->
                    deliveryTime = value
                    isTimeValid = validateTime(value)
                },
                onFilled = { /* No action needed for delivery time */ },
            )
            if (!isTimeValid) {
                Text(text = "Enter a valid time format within working hours", color = Color.Red)
            }
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

            // Center the buttons vertically
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { isPayButtonClicked = true },
                    enabled = isPhoneNumberValid && isDateValid && isTimeValid &&
                            isLocationValid && selectedItems.isNotEmpty()
                ) {
                    Text(text = "Pay")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { orderButtonClicked = true },
                    enabled = isPayButtonClicked && isPhoneNumberValid && isDateValid && isTimeValid &&
                            isLocationValid && selectedItems.isNotEmpty()
                ) {
                    Text(text = "Order")
                }
            }
            OrderConfirmationDialog(
                isVisible = orderButtonClicked,
                onDismiss = { orderButtonClicked = false },
                products = selectedItems,
                totalAmount = totalAmount,
                deliveryLocation = selectedLocation,
                deliveryDate = deliveryDate,
                deliveryTime = deliveryTime
            )

        }
    }
}


// Model class for representing an item
data class Item(val name: String, val price: Double, val imageUrl: Int)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    label: String,
    value: String, // Added parameter
    onValueChange: (String) -> Unit, // Explicitly specified type
    onFilled: (Boolean) -> Unit,
    isNumeric: Boolean = false,
) {
    var text by remember { mutableStateOf(TextFieldValue()) }
    Column {
        Text(text = label)
        TextField(
            value = text,
            onValueChange = {
                val isValidInput = if (isNumeric) {
                    it.text.all { char -> char.isDigit() }
                } else {
                    true // No validation needed for non-numeric inputs
                }
                text = it
                onValueChange(it.text) // Pass the text value to the onValueChange callback
                onFilled(isValidInput && it.text.isNotEmpty())
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


// Date validation
fun validateDate(date: String): Boolean {
    // Expecting format: dd/MM/yyyy or dd.MM.yyyy
    val regex = """\d{2}[./]\d{2}[./]\d{4}""".toRegex()
    return regex.matches(date)
}

// Time validation
fun validateTime(time: String): Boolean {
    // Expecting format: HH:mm (24-hour clock system)
    val regex = """([01]\d|2[0-3]):[0-5]\d""".toRegex()
    // Expecting time between 0500 hrs and 2300 hrs (working hours)
    val timeParts = time.split(":")
    val hours = timeParts[0].toInt()
    return regex.matches(time) && hours in 5..23
}

fun calculateTotalAmount(items: List<Item>): Double {
    return items.sumOf { it.price }
}

val nairobiLocations = listOf(
    "Dagoretti",
    "Embakasi",
    "Kamukunji",
    "Kasarani",
    "Kibra",
    "Langata",
    "Makadara",
    "Mathare",
    "Roysambu",
    "Ruaraka",
    "Starehe",
    "Westlands",
)