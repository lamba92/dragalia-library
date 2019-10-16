package com.github.lamba92.dragalia.usecases

interface UseCaseWithParams<P, O> {
    suspend fun buildAction(params: P): O
}