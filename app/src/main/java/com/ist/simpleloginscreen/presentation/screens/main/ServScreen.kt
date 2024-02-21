package com.ist.ondemand.presentation.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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

@Composable
fun ServiceScreen(navController: NavController, vm: MainViewModel) {
    var selectedOption by remember { mutableStateOf("Hair Products") }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Service Screen")


        BottomNavigationMenu(
            selectedItem = BottomNavigationItem.SERVICES,
            navController = navController
        )


        //Horizontal Bar with Options
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OptionItem(text = "Hair Products") { selectedOption = "Hair Products" }
            OptionItem(text = "Foods") { selectedOption = "Foods" }
            OptionItem(text = "Stationeries") { selectedOption = "Stationeries" }
            OptionItem(text = "Electronics") { selectedOption = "Electronics" }
        }

        //Display Product Grid based on selected option
        when (selectedOption) {
            "Hair Products" -> ProductGrid(hairProducts) { product -> vm.addToCart(product) }
            "Foods" -> ProductGrid(foodProducts) { product -> vm.addToCart(product) }
            "Stationeries" -> ProductGrid(stationeryProducts) { product -> vm.addToCart(product) }
            "Electronics" -> ProductGrid(electronicsProducts) { product -> vm.addToCart(product) }
        }
    }
}

@Composable
fun OptionItem(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    )
}

@Composable
fun ProductGrid(products: List<Product>, onItemClick: (Product) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(products) { product ->
            ProductCard(product, onItemClick)
        }
    }
}

@Composable
fun ProductCard(product: Product, onItemClick: (Product) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemClick(product) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
//                imageUrl = cartItem.itemImage
//                painter = painterResource(id = product.imageResId),
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

