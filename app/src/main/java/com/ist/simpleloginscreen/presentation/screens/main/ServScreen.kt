package com.ist.ondemand.presentation.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ist.simpleloginscreen.data.Product
import com.ist.simpleloginscreen.data.electronicsProducts
import com.ist.simpleloginscreen.data.foodProducts
import com.ist.simpleloginscreen.data.hairProducts
import com.ist.simpleloginscreen.data.stationeryProducts
import com.ist.simpleloginscreen.presentation.MainViewModel
import com.ist.simpleloginscreen.presentation.screens.main.BottomNavigationItem
import com.ist.simpleloginscreen.presentation.screens.main.BottomNavigationMenu
import com.ist.simpleloginscreen.presentation.ui.theme.BurntOrange
import com.ist.simpleloginscreen.presentation.ui.theme.Pink80
import com.ist.ondemand.presentation.screens.main.ProductCard as ProductCard1

@Composable
fun ServiceScreen(navController: NavController, vm: MainViewModel) {
    var selectedOption by remember { mutableStateOf("Hair Products") }
    var isSearchVisible by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        BottomNavigationMenu(
            selectedItem = BottomNavigationItem.SERVICES,
            navController = navController,
            isSearchVisible = isSearchVisible,
            onSearchTextChanged = { text ->
                searchText = text
                selectedOption = ""
            },
            onSearchIconClicked = { isSearchVisible = !isSearchVisible }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Products",
                color = Pink80,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        //Horizontal Bar with Options
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OptionItem(
                text = "Hair Products",
                onClick = { selectedOption = "Hair Products" },
                selected = selectedOption == "Hair Products"
            )
            OptionItem(
                text = "Foods",
                onClick = { selectedOption = "Foods" },
                selected = selectedOption == "Foods"
            )
            OptionItem(
                text = "Stationeries",
                onClick = { selectedOption = "Stationeries" },
                selected = selectedOption == "Stationeries"
            )
            OptionItem(
                text = "Electronics",
                onClick = { selectedOption = "Electronics" },
                selected = selectedOption == "Electronics"
            )
        }

        // Filter products based on search text and selected option
        val filteredProducts = getFilteredProducts(vm, searchText, selectedOption)

        if (filteredProducts.isNotEmpty()) {
            ProductGrid(filteredProducts, vm.cartItems.value) { product ->
                vm.addToCart(product)
            }
        } else {
            Text(
                text = "No matching products found",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }


        //Display Product Grid based on selected option
        when (selectedOption) {
            "Hair Products" -> ProductGrid(
                hairProducts,
                vm.cartItems.value
            ) { product -> vm.addToCart(product) }

            "Foods" -> ProductGrid(foodProducts, vm.cartItems.value) { product ->
                vm.addToCart(
                    product
                )
            }

            "Stationeries" -> ProductGrid(
                stationeryProducts,
                vm.cartItems.value
            ) { product -> vm.addToCart(product) }

            "Electronics" -> ProductGrid(
                electronicsProducts,
                vm.cartItems.value
            ) { product -> vm.addToCart(product) }
        }
    }
}

@Composable
fun OptionItem(text: String, onClick: () -> Unit, selected: Boolean) {
    val color = if (selected) BurntOrange else Color.Black
    Text(
        text = text,
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick),
        color = color
    )
}

@Composable
fun ProductGrid(
    products: List<Product>,
    cartItems: List<MainViewModel.CartItem>,
    onItemClick: (Product) -> Unit,
) {
    LazyColumn {
        items(products.chunked(2)) { rowProducts ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                rowProducts.forEach { product ->
                    ProductCard1(product, onItemClick, cartItems)
                }
            }
        }
    }
}


@Composable
fun ProductCard(
    product: Product,
    onItemClick: (Product) -> Unit,
    cartItems: List<MainViewModel.CartItem>
) {
    val cartItem = cartItems.find { it.itemId == product.id }
//    val quantity = cartItem?.quantity ?: 0 // Default quantity is 0 if item not in cart
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onItemClick(product)
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.name)
            Text(text = "$${product.price}")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { onItemClick(product) }) {
                Text(text = "Add to Cart")
            }
        }
    }
}


fun getFilteredProducts(
    vm: MainViewModel,
    searchText: String,
    selectedOption: String
): List<Product> {
    val allProducts = when (selectedOption) {
        "Hair Products" -> hairProducts
        "Foods" -> foodProducts
        "Stationeries" -> stationeryProducts
        "Electronics" -> electronicsProducts
        else -> emptyList()
    }

    return if (searchText.isBlank()) {
        allProducts
    } else {
        allProducts.filter { it.name.startsWith(searchText, ignoreCase = true) }
    }
}