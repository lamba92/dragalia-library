package com.github.lamba92.dragalialost.domain.usecases

interface UseCaseSuspend<T> {
    suspend fun buildAction(): T
}