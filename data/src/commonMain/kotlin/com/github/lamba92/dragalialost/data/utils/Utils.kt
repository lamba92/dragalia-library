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

internal val sanitizationRegexes = listOf(
    Regex("\\[\\[.*\\|(.*)\\]\\]") to "$1",
    Regex("\\[\\[(.*)\\]\\]") to "$1",
    Regex("color:#(.{6});") to "",
    Regex(" br([A-Z])") to " $1",
    Regex("\\.(\\w)") to ". $1",
    Regex("(\\d)\\. (\\d)") to "$1.$2"
)

internal val sanitizationRemoves = listOf(
    "&lt;span ", "&quot;", "&gt;", "&lt;", "/span", "brbr",
    "style=", "&lt;br&gt;", "\n", "]]", "[[", "'''", "font-weight:bold;"
)

internal val sanitizationSubstitutions = listOf(
    "  " to " ", "br/" to ". "
)

@JvmName("replaceRegex")
fun String.replace(values: Pair<Regex, String>) =
    replace(values.first, values.second)

@JvmName("replaceString")
fun String.replace(values: Pair<String, String>) =
    replace(values.first, values.second)

fun String.remove(chars: String) =
    replace(chars, "")

fun String.sanitize(): String {
    var me = this
    sanitizationRegexes.forEach {
        me = me.replace(it)
    }
    sanitizationRemoves.forEach {
        me = me.remove(it)
    }
    sanitizationSubstitutions.forEach {
        me = me.replace(it)
    }
    return me.replace(Regex(" br([A-Z])"), " $1")

}