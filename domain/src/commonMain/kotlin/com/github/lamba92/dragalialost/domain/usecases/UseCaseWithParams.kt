package com.github.lamba92.dragalialost.domain.usecases

interface UseCaseWithParams<P, O> {
    suspend fun buildAction(params: P): O
}
