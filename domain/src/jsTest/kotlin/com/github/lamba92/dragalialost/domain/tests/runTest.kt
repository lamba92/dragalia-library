package com.github.lamba92.dragalialost.domain.tests

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise

actual fun runTest(function: suspend () -> Unit) =
    GlobalScope.promise { function() }.asDynamic()