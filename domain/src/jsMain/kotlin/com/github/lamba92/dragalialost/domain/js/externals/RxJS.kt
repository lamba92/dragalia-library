@file:JsModule("rxjs")
@file:JsNonModule

package com.github.lamba92.dragalialost.domain.js.externals

external class Observable<T>(function: (Subscriber<T>) -> Unit)

external class Subscriber<T>() {
    fun next(element: T)
    fun complete()
    fun error(err: Throwable)
}