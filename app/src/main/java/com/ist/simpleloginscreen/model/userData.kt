package com.ist.simpleloginscreen.model

data class User(
    val id: String,
    val email: String,
    var username: String? = null,
    var phone: Int? = null,
    var password: String? = null,
    var imageUrl: String? = null,
    var bio: String? = null,
    var roles: List<Roles>? = null,
    var services: List<String>? = null,
    var following: List<String>? = null
) {
    //convert to map for firebase
    fun toMap() = mapOf(
        "id" to id,
        "username" to username,
        "email" to email,
        "phone" to phone,
        "password" to password,
        "imageUrl" to imageUrl,
        "bio" to bio,
        "following" to following,
        "roles" to roles,
        "services" to services
    )
}

enum class Roles {
    customer,
    service_provider,
}
