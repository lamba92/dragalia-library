package com.github.lamba92.dragalialost.data.utils

fun <T1, T2, R> combine(
    first: Iterable<T1>,
    second: Iterable<T2>,
    function: (T1, T2) -> R
): List<R> {
    val list = mutableListOf<R>()
    first.forEach { f ->
        second.forEach { s ->
            list.add(function(f, s))
        }
    }
    return list
}

fun <T1, T2, T3, R> combine(
    first: Iterable<T1>,
    second: Iterable<T2>,
    third: Iterable<T3>,
    function: (T1, T2, T3) -> R
): List<R> {
    val list = mutableListOf<R>()
    first.forEach { f ->
        second.forEach { s ->
            third.forEach { t ->
                list.add(function(f, s, t))
            }
        }
    }
    return list
}

fun <T1, T2, T3, T4, R> combine(
    first: Iterable<T1>,
    second: Iterable<T2>,
    third: Iterable<T3>,
    fourth: Iterable<T4>,
    function: (T1, T2, T3, T4) -> R
): List<R> {
    val list = mutableListOf<R>()
    first.forEach { f ->
        second.forEach { s ->
            third.forEach { t ->
                fourth.forEach { fo ->
                    list.add(function(f, s, t, fo))
                }
            }
        }
    }
    return list
}