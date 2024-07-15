package com.develoberke.model.annotation

import com.develoberke.util.Constants.Companion.DEFAULT_COLUMN_WIDTH

@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
annotation class ExcelColumn(val name: String = "", val width: Int = DEFAULT_COLUMN_WIDTH)
