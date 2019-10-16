package com.github.lamba92.dragalia.usecases

interface UseCase<T> {
    suspend fun buildAction(): T
}