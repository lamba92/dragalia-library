package com.github.lamba92.dragalialost.domain.utils

import com.github.lamba92.dragalialost.domain.usecases.UseCase
import com.github.lamba92.dragalialost.domain.usecases.UseCaseWithParams

@Suppress("SpellCheckingInspection")
fun StringBuilder.appendln(s: String) =
    append("$s\n")

suspend operator fun <P, O> UseCaseWithParams<P, O>.invoke(params: P) =
    buildAction(params)

suspend operator fun <O> UseCase<O>.invoke() =
    buildAction()
