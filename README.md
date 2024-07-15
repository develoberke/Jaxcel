
# Jaxcel

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![Apache poi](https://img.shields.io/badge/apache.poi-5.3.0-green)](https://poi.apache.org/)


Jaxcel, built on Apache POI, simplifies and optimizes the process of exporting data to Excel spreadsheets in Java applications. It provides a class-based approach to defining export configurations, making it straightforward to generate Excel files from your Java objects.


## Features

- Class-Based Configuration
- Apache POI Integration
- Easy to Use
- Flexible




## Usage/Examples

### Kotlin

```kotlin
Jaxcel.generateExcelFile(data = List<yourData>, fileName = "fileName", clazz = YourClass::class.java)

// or to create workbook
Jaxcel.generateExcelWorkbook(data, YourClass.class)
```

### Java

```java
Jaxcel.generateExcelFile(List<yourData>, "fileName", YourClass.class);

// or to create workbook
Jaxcel.generateExcelWorkbook(List<yourData>, YourClass.class)
```



<!--
## Maven dependency


```bash
<dependency>
  <groupId>com.develoberke</groupId>
  <artifactId>Jaxcel</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```
-->

## Quick Overview


```kotlin
// ExcelModel annotation allows you to give a name for your excel sheet
// if not provided, it takes class name by default
@ExcelModel(name = "User Information")
data class User(
    // ExcelColumn annotation allows you to give a name and width for your excel sheet
    // if not provided, it takes field name by default
    @field:ExcelColumn(name = "user id", width = 50) var id: Int,
    val name: String,
    val email: String,
)
```
