package com.github.lamba92.dragalia.modules

import com.github.lamba92.dragalia.mappers.*
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

object MappersModule : KodeinModuleProvider {
    override fun provideModule(): Kodein.Builder.() -> Unit = {
        bind<AdventurersMapper>() with singleton { AdventurersMapper() }
        bind<DragonsMapper>() with singleton { DragonsMapper() }
        bind<WyrmprintsMapper>() with singleton { WyrmprintsMapper() }

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