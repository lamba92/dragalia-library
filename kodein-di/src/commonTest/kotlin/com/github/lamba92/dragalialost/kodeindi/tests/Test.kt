package com.github.lamba92.dragalialost.kodeindi.tests

import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.domain.repositories.ext.searchAdventurers
import com.github.lamba92.dragalialost.kodeindi.dragaliaLostModule
import com.github.lamba92.utils.runTest
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.instance
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Test : KodeinAware {

    override val kodein by Kodein.lazy {
        import(dragaliaLostModule())
    }

    private val repository by instance<DragaliaLostRepository>()

    @ExperimentalTime
    @Test
    fun testAdventurers() = runTest {

        val (res, time) = measureTimedValue { repository.searchAdventurers { } }
        assertEquals(120, res.size)
        val (res2, time2) = measureTimedValue { repository.searchAdventurers { } }
        assertEquals(res, res2)
        assertEquals(true, time > time2)

        println(
            "__________________________\n" +
                    "| TestAdventurers results: " +
                    "| Total results first call:     ${res.size}\n" +
                    "| Total results second call:    ${res2.size}\n" +
                    "| Elapsed time for first call:  ${time.inSeconds}\n" +
                    "| Elapsed time for second call: ${time2.inSeconds}\n" +
                    "|_________________________"
        )
    }

}