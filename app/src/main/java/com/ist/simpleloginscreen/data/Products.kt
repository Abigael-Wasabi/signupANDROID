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
    Product("7", "lashes", 10.78, R.drawable.lashes),
    Product("8", "wig", 1000.43, R.drawable.wig),
    Product("9", "extension", 700.00, R.drawable.extension),
    Product("10", "bottle", 800.00, R.drawable.bottle),
)

//foods data
val foodProducts = listOf(
    Product("11", "Bread", 2.49, R.drawable.bread),
    Product("12", "Milk", 1.99, R.drawable.milk),
    Product("13", "Chocolate", 0.99, R.drawable.chocolate),
    Product("14", "Icecream", 0.99, R.drawable.icecream),
    Product("15", "Soda", 0.99, R.drawable.soda),
    Product("16", "Sweets", 0.99, R.drawable.sweets),
    Product("17", "Grocery", 10.99, R.drawable.grocery),
    Product("18", "fruits", 100.99, R.drawable.fruits),
    Product("19", "salt", 117.00, R.drawable.salt),
    Product("20", "sugar", 100.99, R.drawable.sugar),
)

// stationeries data
val stationeryProducts = listOf(
    Product("21", "Notebook", 3.99, R.drawable.notebook),
    Product("22", "Pens", 0.99, R.drawable.pens),
    Product("23", "bag", 2.99, R.drawable.bag),
    Product("24", "Colors", 0.99, R.drawable.colors),
    Product("25", "Set", 0.99, R.drawable.set),
    Product("26", "Socks", 0.99, R.drawable.socks),
    Product("27", "nail cutter", 900.00, R.drawable.nailcutter),
    Product("28", "shoe", 200.00, R.drawable.shoe),
    Product("29", "rope", 50000.00, R.drawable.rope),
    Product("30", "house", 40.00, R.drawable.house),
)

// electronics data
val electronicsProducts = listOf(
    Product("31", "Smartphone", 599.99, R.drawable.phones),
    Product("32", "Laptop", 899.99, R.drawable.laptop),
    Product("33", "Charger", 500.99, R.drawable.charger),
    Product("34", "Headphones", 83560.99, R.drawable.headphones),
    Product("35", "Plug", 9890.99, R.drawable.plug),
    Product("36", "Pods", 1230.99, R.drawable.pods),
    Product("37", "book", 55700.99, R.drawable.book),
    Product("38", "gascooker", 0.99, R.drawable.gas),
    Product("39", "heater", 9340.99, R.drawable.heater),
    Product("40", "kettle", 670.99, R.drawable.kettle),
    Product("41", "plate", 450.99, R.drawable.plate),
    Product("42", "lighter", 230.99, R.drawable.lighter),
)
