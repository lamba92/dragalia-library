package com.github.lamba92.dragalialost.kodeindi.modules

import com.github.lamba92.dragalialost.data.mappers.*
import com.github.lamba92.dragalialost.kodeindi.KodeinModuleProvider
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

object MappersModule : KodeinModuleProvider {
    override fun provideModule(): Kodein.Builder.() -> Unit = {

        bind<AdventurerMapper>() with singleton {
            AdventurerMapper(
                instance(), instance(), instance(), instance(),
                instance(), instance(), instance(), instance(),
                instance(), instance(), instance()
            )
        }
        bind<DragonsMapper>() with singleton {
            DragonsMapper(
                instance(), instance(), instance(),
                instance(), instance()
            )
        }
        bind<WyrmprintsMapper>() with singleton {
            WyrmprintsMapper(
                instance(), instance(), instance(),
                instance(), instance()
            )
        }

        bind<AdventurersQueryMapper>() with singleton {
            AdventurersQueryMapper(instance(), instance(), instance(), instance())
        }
        bind<DragonsQueryMapper>() with singleton { DragonsQueryMapper(instance(), instance()) }
        bind<WyrmprintsQueryMapper>() with singleton { WyrmprintsQueryMapper(instance(), instance(), instance()) }

        bind<ElementMapper>() with singleton { ElementMapper() }
        bind<HeroClassMapper>() with singleton { HeroClassMapper() }
        bind<RarityMapper>() with singleton { RarityMapper() }
        bind<WeaponTypeMapper>() with singleton { WeaponTypeMapper() }
        bind<WyrmprintAbilityTypeMapper>() with singleton { WyrmprintAbilityTypeMapper() }

    }
}