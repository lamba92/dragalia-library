package com.github.lamba92.dragalialost.domain.utils

import com.github.lamba92.dragalialost.domain.usecases.UseCase
import com.github.lamba92.dragalialost.domain.usecases.UseCaseSuspend
import com.github.lamba92.dragalialost.domain.usecases.UseCaseWithParams
import com.github.lamba92.dragalialost.domain.usecases.UseCaseWithParamsSuspend

fun StringBuilder.appendln(s: String) =
    append("$s\n")

suspend operator fun <P, O> UseCaseWithParamsSuspend<P, O>.invoke(params: P) =
    buildAction(params)

operator fun <P, O> UseCaseWithParams<P, O>.invoke(params: P) =
    buildAction(params)

suspend operator fun <O> UseCaseSuspend<O>.invoke() =
    buildAction()

operator fun <O> UseCase<O>.invoke() =
    buildAction()