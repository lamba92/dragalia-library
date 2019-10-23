package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.*
import com.github.lamba92.dragalialost.domain.entities.AdventurerEntity
import com.github.lamba92.dragalialost.domain.entities.DragaliaEntity
import com.github.lamba92.dragalialost.domain.entities.enums.WeaponType
import com.github.lamba92.dragalialost.domain.entities.enums.WeaponType.*
import com.github.lamba92.dragalialost.domain.entities.support.ManaCircleBonusStats
import com.github.lamba92.dragalialost.domain.entities.support.VoiceActor
import com.soywiz.klock.parseUtc

class AdventurerMapper(
    private val abilityMapper: AbilityMapper,
    private val coAbilityMapper: CoAbilityMapper,
    private val adventurerSkillMapper: AdventurerSkillMapper,
    private val elementMapper: ElementMapper,
    private val weaponTypeMapper: WeaponTypeMapper,
    private val heroClassMapper: HeroClassMapper,
    private val genderMapper: GenderMapper,
    private val raceMapper: RaceMapper,
    private val rarityMapper: RarityMapper,
    private val sourceMapper: SourceMapper,
    private val availabilityMapper: AvailabilityMapper,
    private val imageMapper: ImageMapper
) : SingleFromRemoteMapper<AdventurerMapper.Params, AdventurerEntity> {

    override fun fromRemoteSingle(remote: Params) = with(remote) {
        val coability = coAbilityMapper(
            CoAbilityMapper.Params(coabilityLvl1, coabilityLvl2, coabilityLvl3, coabilityLvl4, coabilityLvl5)
        )
        val skill1 = adventurerSkillMapper(skill1)
        val skill2 = adventurerSkillMapper(skill2)
        val ability1 = abilityMapper(AbilityMapper.Params(ability1lvl1, ability1lvl2, ability1lvl3))
        val ability2 = abilityMapper(AbilityMapper.Params(ability2lvl1, ability2lvl2, ability2lvl3))
        val ability3 = abilityMapper(AbilityMapper.Params(ability3lvl1, ability3lvl2, ability3lvl3))
        val weaponType = weaponTypeMapper(adventurer.WeaponType)
        with(adventurer) {
            AdventurerEntity(
                if (Name !in FullName) "$FullName $Name" else FullName,
                Description.replace("'''", ""),
                MaxHp.toInt(),
                MaxAtk.toInt(),
                DragaliaEntity.ADVENTURERS_MAX_LVL,
                ManaCircleBonusStats(
                    PlusHp0.toInt(),
                    PlusHp1.toInt(),
                    PlusHp2.toInt(),
                    PlusHp3.toInt(),
                    PlusHp4.toInt(),
                    McFullBonusHp5.toInt()
                ),
                ManaCircleBonusStats(
                    PlusAtk0.toInt(),
                    PlusAtk1.toInt(),
                    PlusAtk2.toInt(),
                    PlusAtk3.toInt(),
                    PlusAtk4.toInt(),
                    McFullBonusAtk5.toInt()
                ),
                MinHpAtk + coability.level1.might + skill1.level1.might,
                MaxHp.toInt() + MaxAtk.toInt() + coability.level5.might +
                        (skill1.level3?.might ?: skill1.level2.might) +
                        (skill2.level3?.might ?: skill2.level2.might) +
                        (ability1.level3?.might ?: ability1.level2?.might ?: ability1.level1.might) +
                        (ability2.level3?.might ?: ability1.level2?.might ?: ability2.level1.might) +
                        (ability3.level3?.might ?: ability3.level2?.might ?: ability3.level1.might) +
                        DragaliaEntity.FORCE_STRIKE_LVL2_MIGHT,
                weaponType.defenseProvided,
                heroClassMapper(CharaType),
                genderMapper(Gender),
                raceMapper(Race),
                rarityMapper(Rarity),
                VoiceActor(EnglishCV),
                VoiceActor(JapaneseCV),
                sourceMapper(Obtain),
                DragaliaEntity.DATE_TIME_FORMAT.parseUtc(ReleaseDate.substring(0, 10)),
                availabilityMapper(Availability),
                imageMapper(artworks),
                imageMapper(icons),
                elementMapper(ElementalType),
                weaponType,
                skill1,
                skill2,
                ability1,
                ability2,
                ability3,
                coability
            )
        }
    }

    data class Params(
        val adventurer: AdventurerJSON,
        val ability1lvl2: AbilityJSON,
        val ability1lvl1: AbilityJSON,
        val ability1lvl3: AbilityJSON?,
        val ability2lvl1: AbilityJSON,
        val ability2lvl2: AbilityJSON,
        val ability2lvl3: AbilityJSON?,
        val ability3lvl1: AbilityJSON,
        val ability3lvl2: AbilityJSON?,
        val ability3lvl3: AbilityJSON?,
        val coabilityLvl1: CoAbilityJSON,
        val coabilityLvl2: CoAbilityJSON,
        val coabilityLvl3: CoAbilityJSON,
        val coabilityLvl4: CoAbilityJSON,
        val coabilityLvl5: CoAbilityJSON,
        val skill1: SkillJSON,
        val skill2: SkillJSON,
        val artworks: List<ImageInfoJSON>,
        val icons: List<ImageInfoJSON>
    )

    @Suppress("PrivatePropertyName")
    private val AdventurerJSON.MinHpAtk
        get() = when (Rarity) {
            "3" -> MinHp3.toInt() + MinAtk3.toInt()
            "4" -> MinHp4.toInt() + MinAtk4.toInt()
            else -> MinHp5.toInt() + MinAtk5.toInt()
        }

    private val WeaponType.defenseProvided
        get() = when (this) {
            SWORD, BLADE, DAGGER, AXE, LANCE -> 10
            BOW, WAND, STAFF -> 8
        }
}