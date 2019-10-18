package com.github.lamba92.dragalialost.domain.tests

import kotlinx.coroutines.runBlocking

actual fun runTest(function: suspend () -> Unit) =
    runBlocking { function() }