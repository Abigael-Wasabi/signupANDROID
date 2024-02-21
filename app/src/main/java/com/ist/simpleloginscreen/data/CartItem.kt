package com.ist.simpleloginscreen.data

data class CartItem(
    val itemId: String,
    val itemName: String,
    val itemPrice: Double,
    var quantity: Int,
)
