package com.develoberke

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.nio.file.Files
import java.nio.file.Paths

class Jaxcel {

    companion object {
        @JvmStatic
        @JvmOverloads
        fun <T : Any> generateExcelFile(data: List<T>, path: String? = null, fileName: String, clazz: Class<T>) {
            val workbook = createWorkbook(data, clazz)
            createExcelFile(workbook, fileName, path)
            workbook.close()
        }


        private fun <T : Any> createWorkbook(data: List<T>, clazz: Class<T>): XSSFWorkbook {
            val workbook = XSSFWorkbook()
            val sheet = workbook.createSheet(clazz.simpleName)

            val firstRow = sheet.createRow(0)
            clazz.declaredFields.forEachIndexed { index, field ->
                field.isAccessible = true
                val cell = firstRow.createCell(index)
                cell.setCellValue(field.name)
            }

            data.forEachIndexed { index, item ->
                val row = sheet.createRow(index + 1)
                clazz.declaredFields.forEachIndexed { fieldIndex, field ->
                    field.isAccessible = true
                    val cell = row.createCell(fieldIndex)
                    cell.setCellValue(field.get(item).toString())
                }
            }

            return workbook
        }

        private fun createExcelFile(workbook: XSSFWorkbook, fileName: String, path: String? = null) {
            val filePath = if (path != null) {
                Paths.get(path, "$fileName.xlsx")
            } else {
                val currDir = Paths.get("").toAbsolutePath().toString()
                Paths.get(currDir, "$fileName.xlsx")
            }
            Files.newOutputStream(filePath).use { outputStream ->
                workbook.write(outputStream)
            }
        }
    }
}