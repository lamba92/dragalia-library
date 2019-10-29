package com.github.lamba92.dragalialost.domain.usecases

interface UseCaseWithParams<P, O> {
    fun buildAction(params: P): O

    operator fun invoke(params: P) =
        buildAction(params)

}