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
                instance(), instance(), instance(), instance()
            )
        }
        bind<DragonMapper>() with singleton {
            DragonMapper(
                instance(), instance(), instance(),
                instance(), instance()
            )
        }
        bind<WyrmprintMapper>() with singleton {
            WyrmprintMapper(
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
        bind<AbilityTypeMapper>() with singleton { AbilityTypeMapper() }

        bind<AdventurerAbilityMapper>() with singleton { AdventurerAbilityMapper(instance(), instance(), instance()) }
        bind<AdventurerSkillMapper>() with singleton { AdventurerSkillMapper() }
        bind<AvailabilityMapper>() with singleton { AvailabilityMapper() }
        bind<CharacterTypeMapper>() with singleton { CharacterTypeMapper() }
        bind<CoAbilityMapper>() with singleton { CoAbilityMapper() }
        bind<DragonAbilityMapper>() with singleton { DragonAbilityMapper(instance(), instance(), instance()) }
        bind<DragonSkillMapper>() with singleton { DragonSkillMapper() }
        bind<FeaturedCharacterMapper>() with singleton { FeaturedCharacterMapper() }
        bind<GenderMapper>() with singleton { GenderMapper() }
        bind<RaceMapper>() with singleton { RaceMapper() }
        bind<SourceMapper>() with singleton { SourceMapper(instance()) }
        bind<DifficultyMapper>() with singleton { DifficultyMapper() }
        bind<WyrmprintAbilityMapper>() with singleton { WyrmprintAbilityMapper(instance(), instance(), instance()) }
        bind<ImageMapper>() with singleton { ImageMapper() }
        bind<ElementalResistancesMapper>() with singleton { ElementalResistancesMapper() }
        bind<AfflictionResistancesMapper>() with singleton { AfflictionResistancesMapper(instance()) }
        bind<ResistancePercentageMapper>() with singleton { ResistancePercentageMapper() }

    }
}