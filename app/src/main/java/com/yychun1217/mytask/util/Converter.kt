@file:JvmName("Converter")

package com.yychun1217.mytask.util

import androidx.databinding.InverseMethod

fun convertStringToFloat(value: String?): Float? = value?.toFloatOrNull()

@InverseMethod(value = "convertStringToFloat")
fun convertFloatToString(value: Float?): String? = value?.toString()