package com.ist.simpleloginscreen.data

import com.ist.simpleloginscreen.R


//data structure
data class Product(
    val name: String,
    val price: Double,
    val imageResId: Int // Resource ID of the product image
)

// hair data
val hairProducts = listOf(
    Product("Shampoo", 10.99, R.drawable.shampoo),
    Product("Conditioner", 8.99, R.drawable.conditioner),
    Product("beads", 3.99, R.drawable.beads),
    Product("Clips", 0.99, R.drawable.clips),
    Product("Comb", 0.99, R.drawable.comb),
    Product("Spray", 0.99, R.drawable.spray),
)

//foods data
val foodProducts = listOf(
    Product("Bread", 2.49, R.drawable.bread),
    Product("Milk", 1.99, R.drawable.milk),
    Product("Chocolate", 0.99, R.drawable.chocolate),
    Product("Icecream", 0.99, R.drawable.icecream),
    Product("Soda", 0.99, R.drawable.soda),
    Product("Sweets", 0.99, R.drawable.sweets),
)

// stationeries data
val stationeryProducts = listOf(
    Product("Notebook", 3.99, R.drawable.notebook),
    Product("Pens", 0.99, R.drawable.pens),
    Product("bag", 2.99, R.drawable.bag),
    Product("Colors", 0.99, R.drawable.colors),
    Product("Set", 0.99, R.drawable.set),
    Product("Socks", 0.99, R.drawable.socks),
)

// electronics data
val electronicsProducts = listOf(
    Product("Smartphone", 599.99, R.drawable.phones),
    Product("Laptop", 899.99, R.drawable.laptop),
    Product("Charger", 500.99, R.drawable.charger),
    Product("Headphones", 0.99, R.drawable.headphones),
    Product("Plug", 0.99, R.drawable.plug),
    Product("Pods", 0.99, R.drawable.pods),


    )
