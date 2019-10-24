package com.github.lamba92.dragalialost.kodeindi.tests

import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasource
import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.domain.repositories.ext.*
import com.github.lamba92.dragalialost.kodeindi.dragaliaLostModule
import com.github.lamba92.utils.runTest
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
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
    private val endpoints by instance<GamepediaDatasource.Endpoints>()
    private val datasource by instance<GamepediaDatasource>()

    @ExperimentalTime
    @Test
    fun adventurersTest() = runTest {

        val (res, time) = measureTimedValue { repository.searchAllAdventurers().toList() }
        val (res2, time2) = measureTimedValue { repository.searchAllAdventurers().toList() }
        assertEquals(res, res2)
        assertEquals(true, time > time2)

        println(
            "__________________________\n" +
                    "| TestAdventurers results: \n" +
                    "| Total results first call:     ${res.size}\n" +
                    "| Total results second call:    ${res2.size}\n" +
                    "| Elapsed time for first call:  ${time.inSeconds}\n" +
                    "| Elapsed time for second call: ${time2.inSeconds}\n" +
                    "|_________________________\n"
        )

        res.sortedBy { it.name }.forEach { println(it) }
    }

    @ExperimentalTime
    @Test
    fun singleAdventurerTest() = runTest {
        val (res, time) = measureTimedValue {
            repository.searchAdventurers {
                name = "Euden"
            }.toList()
        }

        assertEquals(2, res.size)
        println(
            "__________________________\n" +
                    "| TestSingleAdventurers results: \n" +
                    "| Elapsed time for first call:  ${time.inSeconds}\n" +
                    "|_________________________"
        )
        println(res)
    }

    @ExperimentalTime
    @Test
    fun dragonsTest() = runTest {
        val (res, time) = measureTimedValue { repository.searchAllDragons().toList() }
        val (res2, time2) = measureTimedValue { repository.searchAllDragons().toList() }
        assertEquals(res, res2)
        assertEquals(true, time > time2)
        println(
            "__________________________\n" +
                    "| TestDragons results: \n" +
                    "| Total results first call:     ${res.size}\n" +
                    "| Total results second call:    ${res2.size}\n" +
                    "| Elapsed time for first call:  ${time.inSeconds}\n" +
                    "| Elapsed time for second call: ${time2.inSeconds}\n" +
                    "|_________________________\n"
        )

        res.sortedBy { it.name }.forEach { println(it) }
    }

    @ExperimentalTime
    @Test
    fun singleDragonTest() = runTest {
        val (res, time) = measureTimedValue {
            repository.searchDragons {
                name = "Agni"
            }.toList()
        }

        assertEquals(1, res.size)
        assertEquals("Agni", res.first().name)

        println(
            "__________________________\n" +
                    "| TestSingleDragon results: \n" +
                    "| Elapsed time for first call:  ${time.inSeconds}\n" +
                    "|_________________________"
        )
        println(res)
    }

    @ExperimentalTime
    @Test
    fun wyrmprintsTest() = runTest {
        val (res, time) = measureTimedValue { repository.searchAllWyrmprints().toList() }
        val (res2, time2) = measureTimedValue { repository.searchAllWyrmprints().toList() }
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
            }.toList()
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

    @Test
    fun test() = runTest {

        repository.searchAdventurers {
            name = "gala"
        }
            .collect {
                println(it)
            }

    }

}