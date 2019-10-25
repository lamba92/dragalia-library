package com.github.lamba92.dragalialost.kodeindi.tests

import com.github.lamba92.utils.runTest
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@ExperimentalTime
fun runTestTimed(function: suspend () -> Unit) = runTest {
    println("Took ${measureTime { function() }.inSeconds} to complete the test")
}