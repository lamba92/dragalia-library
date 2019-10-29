package com.github.lamba92.dragalialost.domain.usecases

interface UseCase<T> {
    fun buildAction(): T
}