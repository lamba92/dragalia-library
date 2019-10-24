package com.github.lamba92.dragalialost.data.utils

import kotlinx.coroutines.Deferred
import kotlin.jvm.JvmName

internal fun String.isNotBlankOrZero() =
    isNotBlank() && this != "0"

internal inline fun <T> String.ifIsNotBlankOrZero(action: (value: String) -> T) =
    if (isNotBlankOrZero()) action(this) else null

@JvmName("mapPairGet")
operator fun <K1, K2, V> Map<Pair<K1, K2>, V>.get(key1: K1, key2: K2) =
    get(key1 to key2)

@JvmName("mutableMapPairGet")
operator fun <K1, K2, V> MutableMap<Pair<K1, K2>, V>.get(key1: K1, key2: K2) =
    get(key1 to key2)

@JvmName("mutableMapTripleGet")
operator fun <K1, K2, K3, V> MutableMap<Triple<K1, K2, K3>, V>.get(key1: K1, key2: K2, keyK3: K3) =
    get(Triple(key1, key2, keyK3))

@JvmName("mapTripleGet")
operator fun <K1, K2, K3, V> Map<Triple<K1, K2, K3>, V>.get(key1: K1, key2: K2, keyK3: K3) =
    get(Triple(key1, key2, keyK3))

@JvmName("mutableMapPairPut")
operator fun <K1, K2, V> MutableMap<Pair<K1, K2>, V>.set(key1: K1, key2: K2, value: V) =
    put(key1 to key2, value)

@JvmName("mutableMapTriplePut")
operator fun <K1, K2, K3, V> MutableMap<Triple<K1, K2, K3>, V>.set(key1: K1, key2: K2, keyK3: K3, value: V) =
    put(Triple(key1, key2, keyK3), value)

fun String.addIfNotEndsWith(suffix: String) =
    if (endsWith(suffix)) this else plus(suffix)

fun String.addIfNotStartsWith(prefix: String) =
    if (startsWith(prefix)) this else prefix + this

fun String.addIfNotWith(prefix: String, suffix: String) =
    addIfNotStartsWith(prefix).addIfNotEndsWith(suffix)

suspend inline fun <A, B, C> Triple<Deferred<A>, Deferred<B>, Deferred<C>>.await() =
    Triple(first.await(), second.await(), third.await())

inline infix fun <T, R> T.with(function: T.() -> R) =
    this to function(this)

inline infix fun <T, R, K> T.withPair(function: T.() -> Pair<R, K>) =
    function(this).let { Triple(this, it.first, it.second) }

internal val sanitizationRegex1 = Regex("(&lt;span )?style=.*&gt;(\\d*%)&lt.*;( |\\.)")
internal val sanitizationRegex2 = Regex("(&lt;span )?style=.*&gt;(\\d{1,2}%) \\((\\d{1,3}%)\\).*")
internal val sanitizationRegex3 = Regex("\\[\\[.*\\|(.*)\\]\\]")
internal val sanitizationRegex4 = Regex("\\[\\[(.*)\\]\\]")
internal val sanitizationRegex5 = Regex("&quot;(.*)\\.&quot;")

fun String.sanitize() =
    replace("'''", "")
        .replace(sanitizationRegex1, "$2 ")
        .replace(sanitizationRegex2, "$2 ($3)")
        .replace(sanitizationRegex3, "$1")
        .replace(sanitizationRegex4, "$1")
        .replace("&lt;br&gt;", "")
        .replace("\n", "")
        .replace(sanitizationRegex5, "$1")