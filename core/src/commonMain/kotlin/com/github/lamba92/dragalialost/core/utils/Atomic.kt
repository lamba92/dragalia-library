package com.github.lamba92.dragalialost.core.utils

import com.github.lamba92.dragalialost.data.utils.get
import com.github.lamba92.dragalialost.data.utils.set
import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.getAndUpdate
import kotlin.jvm.JvmName

internal fun <T> T.toAtomicReference() =
    atomic(this)

@JvmName("atomicMapGet")
internal operator fun <K, V> AtomicRef<Map<K, V>>.get(key: K) =
    value[key]

@JvmName("atomicMutableMapGet")
internal operator fun <K, V> AtomicRef<MutableMap<K, V>>.get(key: K) =
    value[key]

@JvmName("atomicPairMapGet")
internal operator fun <K1, K2, V> AtomicRef<Map<Pair<K1, K2>, V>>.get(key1: K1, key2: K2) =
    value[key1, key2]

@JvmName("atomicPairMutableMapGet")
internal operator fun <K1, K2, V> AtomicRef<MutableMap<Pair<K1, K2>, V>>.get(key1: K1, key2: K2) =
    value[key1, key2]

internal fun <T> AtomicRef<T>.getAndApply(function: T.() -> Unit) =
    getAndUpdate { it.apply(function) }

internal operator fun <K, V> AtomicRef<MutableMap<K, V>>.set(key: K, value_: V) =
    getAndApply { set(key, value_) }

internal operator fun <K1, K2, V> AtomicRef<MutableMap<Pair<K1, K2>, V>>.set(key1: K1, key2: K2, value: V) =
    getAndApply { set(key1, key2, value) }

@JvmName("atomicTripleMapTripleGet")
internal operator fun <K1, K2, K3, V> AtomicRef<Map<Triple<K1, K2, K3>, V>>.get(key1: K1, key2: K2, key3: K3) =
    value[key1, key2, key3]

@JvmName("atomicTripleMutableMapTripleGet")
internal operator fun <K1, K2, K3, V> AtomicRef<MutableMap<Triple<K1, K2, K3>, V>>.get(key1: K1, key2: K2, key3: K3) =
    value[key1, key2, key3]

internal operator fun <K1, K2, K3, V> AtomicRef<MutableMap<Triple<K1, K2, K3>, V>>.set(
    key1: K1, key2: K2, key3: K3, value: V
) = getAndApply { set(key1, key2, key3, value) }
