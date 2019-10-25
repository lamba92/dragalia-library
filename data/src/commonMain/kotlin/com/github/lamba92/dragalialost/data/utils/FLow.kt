package com.github.lamba92.dragalialost.data.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*

inline fun <T, R> Flow<T>.scopedMap(crossinline transform: suspend CoroutineScope.(value: T) -> R) =
    map { coroutineScope { transform(it) } }

inline fun <T, R> Flow<T>.deferredMap(crossinline transform: suspend (value: T) -> R) =
    scopedMap { async { transform(it) } }
        .map { it.await() }

@FlowPreview
inline fun <T, R> Flow<T>.flatMapIterableConcat(crossinline transform: suspend (value: T) -> Iterable<R>) =
    flatMapConcat { transform(it).asFlow() }

@FlowPreview
fun <T> Flow<Iterable<T>>.flattenConcatIterable() =
    flatMapConcat { it.asFlow() }

@FlowPreview
fun <T> Flow<Iterable<T>>.flattenMergeIterable() =
    flatMapMerge { it.asFlow() }

inline fun <T, R> Flow<T>.pairMap(crossinline function: suspend (T) -> R) =
    map { it to function(it) }

inline fun <T, R, L> Flow<T>.tripleMap(crossinline function: suspend (T) -> Pair<R, L>) =
    map { function(it).let { (f, s) -> Triple(f, s, it) } }

@FlowPreview
operator fun <T> Flow<T>.plus(other: Flow<T>) =
    flowOf(this, other).flattenMerge()