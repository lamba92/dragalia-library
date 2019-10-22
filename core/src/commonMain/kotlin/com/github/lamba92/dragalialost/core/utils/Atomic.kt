package com.github.lamba92.dragalialost.core.utils

import com.github.lamba92.dragalialost.data.utils.*
import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.getAndUpdate
import kotlin.collections.set
import kotlin.jvm.JvmName

internal fun <T> T.toAtomicReference() =
    atomic(this)

@JvmName("atomicMapGet")
internal operator fun <K, V> AtomicRef<Map<K, V>>.get(key: K) =
    value[key]

@JvmName("atomicMutableMapGet")
internal operator fun <K, V> AtomicRef<MutableMap<K, V>>.get(key: K) =
    value[key]

internal operator fun <K, V> AtomicRef<MutableMap<K, V>>.set(key: K, value_: V) =
    getAndUpdate { it.also { it[key] = value_ } }

@JvmName("atomicPairKeyMapSet")
internal operator fun <K1, K2, V> AtomicRef<Map<Pair<K1, K2>, V>>.get(key1: K1, key2: K2) =
    value[key1, key2]

@JvmName("atomicPairKeyMutableMapSet")
internal operator fun <K1, K2, V> AtomicRef<MutableMap<Pair<K1, K2>, V>>.get(key1: K1, key2: K2) =
    value[key1, key2]

internal operator fun <K1, K2, V> AtomicRef<MutableMap<Pair<K1, K2>, V>>.set(key1: K1, key2: K2, value: V) =
    getAndUpdate { it.also { it[key1, key2] = value } }

internal fun <T> AtomicRef<T>.getAndApply(function: T.() -> Unit) =
    getAndUpdate { it.apply(function) }
