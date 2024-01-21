package com.ist.simpleloginscreen.data

// firebase requires empty constructor thus initalize to null

data class UserData(
    var userId: String? = null,
    var name: String? = null,
    var username: String? = null,
    var imageUrl: String? = null,
    var bio: String? = null,
    var role: List<Roles>? = null,
    var services: List<Services>? = null
) {
    fun toMap() = mapOf(
        "userId" to userId,
        "name" to name,
        "username" to username,
        "imageUrl" to imageUrl,
        "bio" to bio,
        "role" to role,
        "services" to services
    )
}