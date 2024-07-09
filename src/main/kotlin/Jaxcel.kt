package com.develoberke

class Jaxcel {

    fun <T: Any> generateExcelFile(data: List<T>, fileName: String) {
        println("Generating Excel file $fileName")
    }
}