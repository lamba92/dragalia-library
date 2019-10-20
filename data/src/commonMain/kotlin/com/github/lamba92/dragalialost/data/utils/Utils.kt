package com.github.lamba92.dragalialost.data.utils

operator fun Regex.contains(text: CharSequence) =
    matches(text)