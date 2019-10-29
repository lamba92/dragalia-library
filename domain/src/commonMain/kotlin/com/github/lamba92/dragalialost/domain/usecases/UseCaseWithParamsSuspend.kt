package com.github.lamba92.dragalialost.domain.usecases

interface UseCaseWithParamsSuspend<P, O> {
    suspend fun buildAction(params: P): O
}