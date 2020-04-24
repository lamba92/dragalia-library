package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.*
import com.github.lamba92.dragalialost.data.utils.sanitize
import com.github.lamba92.dragalialost.domain.entities.AdventurerEntity
import com.github.lamba92.dragalialost.domain.entities.DragaliaEntity
import com.github.lamba92.dragalialost.domain.entities.DragaliaId
import com.github.lamba92.dragalialost.domain.entities.enums.AbilityType
import com.github.lamba92.dragalialost.domain.entities.enums.Affliction
import com.github.lamba92.dragalialost.domain.entities.enums.Element
import com.github.lamba92.dragalialost.domain.entities.enums.WeaponType
import com.github.lamba92.dragalialost.domain.entities.enums.WeaponType.*
import com.github.lamba92.dragalialost.domain.entities.support.AdventurerAbility
import com.github.lamba92.dragalialost.domain.entities.support.ManaCircleBonusStats
import com.github.lamba92.dragalialost.domain.entities.support.VoiceActor
import com.soywiz.klock.parseUtc

class AdventurerMapper(
    private val adventurerAbilityMapper: AdventurerAbilityMapper,
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
    private val adventurerImageMapper: AdventurerImageMapper
) : SingleFromRemoteMapper<AdventurerMapper.Params, AdventurerEntity> {

    override fun fromRemoteSingle(remote: Params) = with(remote) {
        val coability = coAbilityMapper(
            CoAbilityMapper.Params(coabilityLvl1, coabilityLvl2, coabilityLvl3, coabilityLvl4, coabilityLvl5)
        )
        val skill1 = adventurerSkillMapper(skill1)
        val skill2 = adventurerSkillMapper(skill2)
        val ability1 = adventurerAbilityMapper(AdventurerAbilityMapper.Params(ability1lvl1, ability1lvl2, ability1lvl3))
        val ability2 = adventurerAbilityMapper(AdventurerAbilityMapper.Params(ability2lvl1, ability2lvl2, ability2lvl3))
        val ability3 = adventurerAbilityMapper(AdventurerAbilityMapper.Params(ability3lvl1, ability3lvl2, ability3lvl3))
        val weaponType = weaponTypeMapper(adventurer.WeaponType)
        with(adventurer) {
            val bonusHp = ManaCircleBonusStats(
                PlusHp0.toInt(),
                PlusHp1.toInt(),
                PlusHp2.toInt(),
                PlusHp3.toInt(),
                PlusHp4.toInt(),
                McFullBonusHp5.toInt()
            )
            val bonusStr = ManaCircleBonusStats(
                PlusAtk0.toInt(),
                PlusAtk1.toInt(),
                PlusAtk2.toInt(),
                PlusAtk3.toInt(),
                PlusAtk4.toInt(),
                McFullBonusAtk5.toInt()
            )
            AdventurerEntity(
                DragaliaId(Id, VariationId),
                if (Name !in FullName) "$FullName $Name" else FullName,
                DragaliaEntity.ADVENTURERS_MAX_LVL,
                MaxHp.toInt(),
                MaxAtk.toInt(),
                MinHpAtk + coability.level1.might + skill1.level1.might,
                MaxHp.toInt() + MaxAtk.toInt() + coability.level5.might +
                        (skill1.level3?.might ?: skill1.level2.might) +
                        (skill2.level3?.might ?: skill2.level2.might) +
                        (ability1.level3?.might ?: ability1.level2?.might ?: ability1.level1.might) +
                        (ability2.level3?.might ?: ability1.level2?.might ?: ability2.level1.might) +
                        (ability3.level3?.might ?: ability3.level2?.might ?: ability3.level1.might) +
                        DragaliaEntity.FORCE_STRIKE_LVL2_MIGHT + bonusHp.total + bonusStr.total,
                rarityMapper(Rarity),
                sourceMapper(Obtain),
                DragaliaEntity.DATE_TIME_FORMAT.parseUtc(ReleaseDate.substring(0, 10)),
                availabilityMapper(Availability),
                adventurerImageMapper(icons),
                adventurerImageMapper(artworks),
                Description.sanitize(),
                bonusHp,
                searchAbilityTypes(ability1, ability2, ability3),
                bonusStr,
                weaponType.defenseProvided,
                heroClassMapper(CharaType),
                genderMapper(Gender),
                raceMapper(Race),
                VoiceActor(EnglishCV),
                VoiceActor(JapaneseCV),
                elementMapper(ElementalType),
                weaponType,
                skill1,
                skill2,
                ability1,
                ability2,
                ability3,
                coability,
                searchAfflictionResistances(ability1, ability2, ability3)
            )
        }
    }

    data class Params(
        val adventurer: AdventurerJSON,
        val ability1lvl1: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>,
        val ability1lvl2: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>,
        val ability1lvl3: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>?,
        val ability2lvl1: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>,
        val ability2lvl2: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>,
        val ability2lvl3: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>?,
        val ability3lvl1: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>,
        val ability3lvl2: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>?,
        val ability3lvl3: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>?,
        val coabilityLvl1: Pair<CoAbilityJSON, ImageInfoJSON?>,
        val coabilityLvl2: Pair<CoAbilityJSON, ImageInfoJSON?>,
        val coabilityLvl3: Pair<CoAbilityJSON, ImageInfoJSON?>,
        val coabilityLvl4: Pair<CoAbilityJSON, ImageInfoJSON?>,
        val coabilityLvl5: Pair<CoAbilityJSON, ImageInfoJSON?>,
        val skill1: Pair<SkillJSON, Triple<ImageInfoJSON?, ImageInfoJSON?, ImageInfoJSON?>>,
        val skill2: Pair<SkillJSON, Triple<ImageInfoJSON?, ImageInfoJSON?, ImageInfoJSON?>>,
        val artworks: List<Pair<Int, ImageInfoJSON?>>,
        val icons: List<Pair<Int, ImageInfoJSON?>>
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

    private val ManaCircleBonusStats.total
        get() = circle1 + circle2 + circle3 + circle4 + circle5 + finalBonus

    private fun searchAfflictionResistances(
        ability1: AdventurerAbility,
        ability2: AdventurerAbility?,
        ability3: AdventurerAbility?
    ) = mutableSetOf<Affliction>().apply {
        addAll(ability1.level1.afflictionResistances.keys)
        ability1.level2?.afflictionResistances?.keys?.let { addAll(it) }
        ability1.level3?.afflictionResistances?.keys?.let { addAll(it) }
        ability2?.level1?.afflictionResistances?.keys?.let { addAll(it) }
        ability2?.level2?.afflictionResistances?.keys?.let { addAll(it) }
        ability2?.level3?.afflictionResistances?.keys?.let { addAll(it) }
        ability3?.level1?.afflictionResistances?.keys?.let { addAll(it) }
        ability3?.level2?.afflictionResistances?.keys?.let { addAll(it) }
        ability3?.level3?.afflictionResistances?.keys?.let { addAll(it) }
    }

    private fun searchElementalResistances(
        ability1: AdventurerAbility,
        ability2: AdventurerAbility?,
        ability3: AdventurerAbility?
    ) = mutableSetOf<Element>().apply {
        addAll(ability1.level1.elementalResistances.keys)
        ability1.level2?.elementalResistances?.keys?.let { addAll(it) }
        ability1.level3?.elementalResistances?.keys?.let { addAll(it) }
        ability2?.level1?.elementalResistances?.keys?.let { addAll(it) }
        ability2?.level2?.elementalResistances?.keys?.let { addAll(it) }
        ability2?.level3?.elementalResistances?.keys?.let { addAll(it) }
        ability3?.level1?.elementalResistances?.keys?.let { addAll(it) }
        ability3?.level2?.elementalResistances?.keys?.let { addAll(it) }
        ability3?.level3?.elementalResistances?.keys?.let { addAll(it) }
    }

    private fun searchAbilityTypes(
        ability1: AdventurerAbility,
        ability2: AdventurerAbility?,
        ability3: AdventurerAbility?
    ) = mutableSetOf<AbilityType>().apply {
        add(ability1.level1.abilityType)
        ability1.level2?.abilityType?.let { add(it) }
        ability1.level3?.abilityType?.let { add(it) }
        ability2?.level1?.abilityType?.let { add(it) }
        ability2?.level2?.abilityType?.let { add(it) }
        ability2?.level3?.abilityType?.let { add(it) }
        ability3?.level1?.abilityType?.let { add(it) }
        ability3?.level2?.abilityType?.let { add(it) }
        ability3?.level3?.abilityType?.let { add(it) }
    }

}
