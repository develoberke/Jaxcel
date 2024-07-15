package com.develoberke

import com.develoberke.model.annotation.ExcelColumn
import com.develoberke.model.annotation.ExcelModel
import com.develoberke.util.Constants.Companion.DEFAULT_COLUMN_WIDTH
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.nio.file.Files
import java.nio.file.Paths

class Jaxcel {

    companion object {
        @JvmStatic
        @JvmOverloads
        fun <T : Any> generateExcelFile(data: List<T>, path: String? = null, fileName: String, clazz: Class<T>) {
            val workbook = generateExcelWorkbook(data, clazz)
            createExcelFile(workbook, fileName, path)
            workbook.close()
        }


        fun <T : Any> generateExcelWorkbook(data: List<T>, clazz: Class<T>): XSSFWorkbook {
            val workbook = XSSFWorkbook()
            val sheetName = clazz.getAnnotation(ExcelModel::class.java)
                ?.takeIf { it.name.isNotEmpty() || it.name.isNotBlank() }?.name
                ?: clazz.simpleName


            val sheet = workbook.createSheet(sheetName)
            createColumnNamesRow(sheet, clazz)
            createDataRows(sheet, data, clazz)

            return workbook
        }

        private fun <T : Any> createColumnNamesRow(sheet: XSSFSheet, clazz: Class<T>) {
            val firstRow = sheet.createRow(0)
            clazz.declaredFields.forEachIndexed { index, field ->
                field.isAccessible = true


                val excelColumn = field.getAnnotation(ExcelColumn::class.java)

                val colName = excelColumn?.takeIf { it.name.isNotEmpty() || it.name.isNotBlank() }?.name
                    ?: field.name

                val colWidth = excelColumn?.takeIf { it.width > 0 }?.width ?: DEFAULT_COLUMN_WIDTH

                val cell = firstRow.createCell(index)
                cell.setCellValue(colName)
                sheet.setColumnWidth(index, colWidth * 256)
            }
        }

        private fun <T : Any> createDataRows(sheet: XSSFSheet, data: List<T>, clazz: Class<T>) {
            data.forEachIndexed { index, item ->
                val row = sheet.createRow(index + 1)
                clazz.declaredFields.forEachIndexed { fieldIndex, field ->
                    field.isAccessible = true
                    val cell = row.createCell(fieldIndex)
                    cell.setCellValue(field.get(item).toString())
                }
            }
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