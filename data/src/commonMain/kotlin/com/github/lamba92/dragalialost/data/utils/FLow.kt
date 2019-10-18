package com.github.lamba92.dragalialost.data.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map

inline fun <T, R> Flow<T>.scopedMap(crossinline transform: suspend CoroutineScope.(value: T) -> R) =
    map { coroutineScope { transform(it) } }

inline fun <T, R> Flow<T>.deferredMap(crossinline transform: suspend (value: T) -> R) =
    scopedMap { async { transform(it) } }
        .map { it.await() }

@FlowPreview
inline fun <T, R> Flow<T>.flatMapIterableConcat(crossinline transform: suspend (value: T) -> Iterable<R>) =
    flatMapConcat { transform(it).asFlow() }