package com.github.lamba92.dragalialost.domain.usecases

interface UseCase<T> {
    suspend fun buildAction(): T
}
