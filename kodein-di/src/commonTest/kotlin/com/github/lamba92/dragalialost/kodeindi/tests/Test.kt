package com.github.lamba92.dragalialost.kodeindi.tests

import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.domain.repositories.searchAdventurers
import com.github.lamba92.dragalialost.domain.repositories.searchAllAdventurers
import com.github.lamba92.dragalialost.kodeindi.dragaliaLostModule
import com.github.lamba92.utils.runTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.instance

class AdventurerTests : KodeinAware {

    override val kodein by Kodein.lazy {
        import(dragaliaLostModule())
    }

    private val repository by instance<DragaliaLostRepository>()

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun f() = runTest {
        repository.searchAllAdventurers()
            .flatMapMerge {
                flowOf(
                    it.skill1.level1.description,
                    it.skill1.level2.description,
                    it.skill1.level3?.description,
                    it.skill2.level1.description,
                    it.skill2.level2.description,
                    it.skill2.level3?.description,

                    it.ability1.level1.description,
                    it.ability1.level2?.description,
                    it.ability1.level3?.description,

                    it.ability2?.level1?.description,
                    it.ability2?.level2?.description,
                    it.ability2?.level3?.description,

                    it.ability3?.level1?.description,
                    it.ability3?.level2?.description,
                    it.ability3?.level3?.description,

                    it.coAbility.level1.description,
                    it.coAbility.level2.description,
                    it.coAbility.level3.description,
                    it.coAbility.level4.description,
                    it.coAbility.level5.description
                ).filterNotNull()
            }
            .distinctUntilChanged()
            .toList()
            .sorted()
            .forEach { println(it) }
    }

    fun f2() = runTest {
        repository.searchAdventurers {
            name = "Lathna"
        }.collect {
            println(it)
        }
    }

}
