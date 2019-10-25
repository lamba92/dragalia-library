package com.github.lamba92.dragalialost.data.js.repository

import com.github.lamba92.dragalialost.domain.js.externals.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
fun <T> Flow<T>.toObservable() =
    Observable<T> { subscriber ->
        onEach { e -> subscriber.next(e) }
            .onCompletion { subscriber.complete() }
            .launchIn(GlobalScope)
    }