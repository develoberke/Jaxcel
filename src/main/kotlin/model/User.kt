package com.develoberke.model

import com.develoberke.model.annotation.ExcelColumn
import com.develoberke.model.annotation.ExcelModel

@ExcelModel(name = "User Information")
data class User(
    @field:ExcelColumn(name = "user id", width = 100) var id: Int,
    val name: String,
    val email: String,
    val password: String
)
