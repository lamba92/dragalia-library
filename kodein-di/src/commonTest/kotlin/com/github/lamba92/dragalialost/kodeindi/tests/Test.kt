package com.github.lamba92.dragalialost.kodeindi.tests

import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.domain.repositories.ext.searchAdventurers
import com.github.lamba92.dragalialost.domain.repositories.ext.searchDragons
import com.github.lamba92.dragalialost.domain.repositories.ext.searchWyrmprints
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
    fun adventurersTest() = runTest {

        val (res, time) = measureTimedValue { repository.searchAdventurers { } }
        assertEquals(120, res.size)
        val (res2, time2) = measureTimedValue { repository.searchAdventurers { } }
        assertEquals(res, res2)
        assertEquals(true, time > time2)

        println(
            "__________________________\n" +
                    "| TestAdventurers results: \n" +
                    "| Total results first call:     ${res.size}\n" +
                    "| Total results second call:    ${res2.size}\n" +
                    "| Elapsed time for first call:  ${time.inSeconds}\n" +
                    "| Elapsed time for second call: ${time2.inSeconds}\n" +
                    "|_________________________"
        )
    }

    @ExperimentalTime
    @Test
    fun singleAdventurerTest() = runTest {
        val (res, time) = measureTimedValue {
            repository.searchAdventurers {
                name = "Euden"
            }
        }

        assertEquals(1, res.size)
        assertEquals("Euden", res.first().name)
        println(
            "__________________________\n" +
                    "| TestSingleAdventurers results: \n" +
                    "| Elapsed time for first call:  ${time.inSeconds}\n" +
                    "|_________________________"
        )
    }

    @ExperimentalTime
    @Test
    fun dragonsTest() = runTest {
        val (res, time) = measureTimedValue { repository.searchDragons { } }
        val (res2, time2) = measureTimedValue { repository.searchDragons { } }
        assertEquals(res, res2)
        assertEquals(true, time > time2)
        println(
            "__________________________\n" +
                    "| TestDragons results: \n" +
                    "| Total results first call:     ${res.size}\n" +
                    "| Total results second call:    ${res2.size}\n" +
                    "| Elapsed time for first call:  ${time.inSeconds}\n" +
                    "| Elapsed time for second call: ${time2.inSeconds}\n" +
                    "|_________________________"
        )
    }

    @ExperimentalTime
    @Test
    fun singleDragonTest() = runTest {
        val (res, time) = measureTimedValue {
            repository.searchDragons {
                name = "Agni"
            }
        }

        assertEquals(1, res.size)
        assertEquals("Agni", res.first().name)

        println(
            "__________________________\n" +
                    "| TestSingleDragon results: \n" +
                    "| Elapsed time for first call:  ${time.inSeconds}\n" +
                    "|_________________________"
        )
    }

    @ExperimentalTime
    @Test
    fun wyrmprintsTest() = runTest {
        val (res, time) = measureTimedValue { repository.searchWyrmprints { } }
        val (res2, time2) = measureTimedValue { repository.searchWyrmprints { } }
        assertEquals(res, res2)
        assertEquals(true, time > time2)
        println(
            "__________________________\n" +
                    "| TestWyrmprints results: \n" +
                    "| Total results first call:     ${res.size}\n" +
                    "| Total results second call:    ${res2.size}\n" +
                    "| Elapsed time for first call:  ${time.inSeconds}\n" +
                    "| Elapsed time for second call: ${time2.inSeconds}\n" +
                    "|_________________________"
        )
    }

    @ExperimentalTime
    @Test
    fun singleWyrmprintTest() = runTest {
        val (res, time) = measureTimedValue {
            repository.searchWyrmprints {
                name = "Resounding Rendition"
            }
        }

        assertEquals(1, res.size)
        assertEquals("Resounding Rendition", res.first().name)

        println(
            "__________________________\n" +
                    "| TestSingleWyrmprint results: \n" +
                    "| Elapsed time for first call:  ${time.inSeconds}\n" +
                    "|_________________________"
        )
    }

}