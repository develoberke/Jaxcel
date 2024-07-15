package com.develoberke

import com.develoberke.model.User

fun main() {
    val users = listOf(
        User(1, "John Doe", "mail@mail.com"),
        User(2, "Jane Doe", "mail@mail.com"),
        User(3, "John Smith", "mail@mail.com"),
        User(4, "Jane Smith", "mail@mail.com"),
        User(5, "berke reis", "mail@mail.com")
    )

    Jaxcel.generateExcelFile(data = users, fileName = "users", clazz = User::class.java)
}