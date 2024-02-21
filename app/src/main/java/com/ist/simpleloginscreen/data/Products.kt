package com.ist.simpleloginscreen.data

import com.ist.simpleloginscreen.R


//data structure
data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val imageResId: Int, // Resource ID of the product image
)

// hair data
val hairProducts = listOf(
    Product("1", "Shampoo", 10.99, R.drawable.shampoo),
    Product("2", "Conditioner", 8.99, R.drawable.conditioner),
    Product("3", "beads", 3.99, R.drawable.beads),
    Product("4", "Clips", 0.99, R.drawable.clips),
    Product("5", "Comb", 0.99, R.drawable.comb),
    Product("6", "Spray", 0.99, R.drawable.spray),
)

//foods data
val foodProducts = listOf(
    Product("1", "Bread", 2.49, R.drawable.bread),
    Product("2", "Milk", 1.99, R.drawable.milk),
    Product("3", "Chocolate", 0.99, R.drawable.chocolate),
    Product("4", "Icecream", 0.99, R.drawable.icecream),
    Product("5", "Soda", 0.99, R.drawable.soda),
    Product("6", "Sweets", 0.99, R.drawable.sweets),
)

// stationeries data
val stationeryProducts = listOf(
    Product("1", "Notebook", 3.99, R.drawable.notebook),
    Product("2", "Pens", 0.99, R.drawable.pens),
    Product("3", "bag", 2.99, R.drawable.bag),
    Product("4", "Colors", 0.99, R.drawable.colors),
    Product("5", "Set", 0.99, R.drawable.set),
    Product("6", "Socks", 0.99, R.drawable.socks),
)

// electronics data
val electronicsProducts = listOf(
    Product("1", "Smartphone", 599.99, R.drawable.phones),
    Product("2", "Laptop", 899.99, R.drawable.laptop),
    Product("3", "Charger", 500.99, R.drawable.charger),
    Product("4", "Headphones", 0.99, R.drawable.headphones),
    Product("5", "Plug", 0.99, R.drawable.plug),
    Product("6", "Pods", 0.99, R.drawable.pods),


    )
