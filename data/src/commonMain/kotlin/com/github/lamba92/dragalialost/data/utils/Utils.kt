package com.github.lamba92.dragalialost.data.utils

internal fun String.isNotBlankOrZero() =
    isNotBlank() && this != "0"

internal inline fun <T> String.ifIsNotBlankOrZero(action: (value: String) -> T) =
    if (isNotBlankOrZero()) action(this) else null