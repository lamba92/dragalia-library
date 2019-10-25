package com.github.lamba92.dragalialost.kodeindi.tests

import com.github.lamba92.dragalialost.domain.entities.enums.AbilityType
import com.github.lamba92.dragalialost.domain.entities.enums.HeroCLass
import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.domain.repositories.searchAdventurers
import com.github.lamba92.dragalialost.domain.repositories.searchWyrmprints
import com.github.lamba92.dragalialost.kodeindi.dragaliaLostModule
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.instance
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

class AdventurerTests : KodeinAware {

    override val kodein by Kodein.lazy {
        import(dragaliaLostModule())
    }

    private val repository by instance<DragaliaLostRepository>()

    @FlowPreview
    @ExperimentalTime
    @Test
    fun testEuden() = runTestTimed {
        repository.searchWyrmprints {
            addAbilityType(AbilityType.EVENT_PERKS)
        }
            .collect { println(it) }
    }

    @ExperimentalTime
    @Test
    fun testHeroClassSingleFilter() = runTestTimed {
        val attackers = repository.searchAdventurers {
            addHeroClass(HeroCLass.ATTACK)
        }

        attackers.collect { adventurer ->
            println(adventurer.name)
        }
    }

    @ExperimentalTime
    @Test
    fun testHeroClassMultipleFilters() = runTestTimed {
        repository.searchAdventurers {
            addHeroClass(HeroCLass.ATTACK)
            addHeroClass(HeroCLass.HEALING)
        }.collect {
            assertEquals(
                true, it.heroClass == HeroCLass.ATTACK || it.heroClass == HeroCLass.HEALING,
                "${it.name} is not HeroCLass.ATTACK or HeroCLass.HEALING, it is ${it.heroClass}"
            )
        }
    }


}
