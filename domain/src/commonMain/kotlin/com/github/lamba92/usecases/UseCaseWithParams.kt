package com.github.lamba92.usecases

interface UseCaseWithParams<P, O> {
    suspend fun buildAction(params: P): O
}