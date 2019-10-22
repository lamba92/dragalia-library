package com.github.lamba92.dragalialost.data.utils

import kotlin.jvm.JvmName

internal fun String.isNotBlankOrZero() =
    isNotBlank() && this != "0"

internal inline fun <T> String.ifIsNotBlankOrZero(action: (value: String) -> T) =
    if (isNotBlankOrZero()) action(this) else null

@JvmName("mapGet")
operator fun <K1, K2, V> Map<Pair<K1, K2>, V>.get(key1: K1, key2: K2) =
    get(key1 to key2)

@JvmName("mutableMapGet")
operator fun <K1, K2, V> MutableMap<Pair<K1, K2>, V>.get(key1: K1, key2: K2) =
    get(key1 to key2)

operator fun <K1, K2, V> MutableMap<Pair<K1, K2>, V>.set(key1: K1, key2: K2, value: V) =
    put(key1 to key2, value)