package com.develoberke

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.nio.file.Files
import kotlin.io.path.createTempFile

class Jaxcel {

    companion object {
        @JvmStatic
        inline fun <reified T> generateExcelFile(data: List<T>, fileName: String) {

            val workbook = createWorkbook(data)

            createExcelFile(workbook, fileName)

            workbook.close()
        }

        inline fun <reified T> createWorkbook(data: List<T>): XSSFWorkbook {
            val workbook = XSSFWorkbook()
            val sheet = workbook.createSheet(T::class.java.simpleName)

            T::class.java.declaredFields.forEachIndexed { index, field ->
                val row = sheet.createRow(0)
                val cell = row.createCell(index)
                cell.setCellValue(field.name)
            }

            data.forEachIndexed { index, item ->
                val row = sheet.createRow(index + 1)
                T::class.java.declaredFields.forEachIndexed { fieldIndex, field ->
                    val cell = row.createCell(fieldIndex)
                    cell.setCellValue(field.get(item).toString())
                }
            }

            return workbook
        }

        fun createExcelFile(workbook: XSSFWorkbook, fileName: String) {
            val temp = createTempFile(fileName, ".xlsx")
            Files.newOutputStream(temp).use { outputStream ->
                workbook.write(outputStream)
                println("Excel file written successfully to: $temp")
            }
        }
    }
}