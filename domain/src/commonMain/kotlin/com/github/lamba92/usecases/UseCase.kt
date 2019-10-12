package com.github.lamba92.usecases

interface UseCase<T> {
    suspend fun buildAction(): T
}