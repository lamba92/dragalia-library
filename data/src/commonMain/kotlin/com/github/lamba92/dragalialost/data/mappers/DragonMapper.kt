package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.*
import com.github.lamba92.dragalialost.domain.entities.DragaliaEntity
import com.github.lamba92.dragalialost.domain.entities.DragaliaId
import com.github.lamba92.dragalialost.domain.entities.DragonEntity
import com.github.lamba92.dragalialost.domain.entities.enums.AbilityType
import com.github.lamba92.dragalialost.domain.entities.enums.Affliction
import com.github.lamba92.dragalialost.domain.entities.enums.Element
import com.github.lamba92.dragalialost.domain.entities.enums.Gender
import com.github.lamba92.dragalialost.domain.entities.support.DragonAbility
import com.github.lamba92.dragalialost.domain.entities.support.SellValue
import com.github.lamba92.dragalialost.domain.entities.support.VoiceActor
import com.soywiz.klock.parseUtc

class DragonMapper(
    private val dragonAbilityMapper: DragonAbilityMapper,
    private val dragonSkillMapper: DragonSkillMapper,
    private val rarityMapper: RarityMapper,
    private val availabilityMapper: AvailabilityMapper,
    private val elementMapper: ElementMapper
) : SingleFromRemoteMapper<DragonMapper.Params, DragonEntity> {

    override fun fromRemoteSingle(remote: Params) = with(remote) {
        val ability1 = dragonAbilityMapper(DragonAbilityMapper.Params(ability1lvl1, ability1lvl2))
        val ability2 = if (ability2lvl1 != null && ability2lvl2 != null)
            dragonAbilityMapper(DragonAbilityMapper.Params(ability2lvl1, ability2lvl2))
        else
            null
        val skill = dragonSkillMapper(skill1)
        with(dragon) {
            DragonEntity(
                DragaliaId(Id),
                if (Name !in FullName) "$FullName $Name" else FullName,
                ProfileText,
                MaxHp.toInt(),
                MaxAtk.toInt(),
                DragaliaEntity.DRAGONS_MAX_LVL,
                MinHp.toInt() + MinAtk.toInt() + ability1.level1.might +
                        skill.level1.might + DragaliaEntity.DRAGON_BOND_MIGHT_PER_LEVEL,
                MaxHp.toInt() + MaxAtk.toInt() + skill.level2.might +
                        ability1.level2.might + (ability2?.level2?.might ?: 0) +
                        DragaliaEntity.DRAGON_BOND_MIGHT_PER_LEVEL * 30,
                rarityMapper(Rarity),
                SellValue(SellCoin.toInt(), SellDewPoint.toInt()),
                VoiceActor(EnglishCV),
                VoiceActor(JapaneseCV),
                Gender.MALE, //TODO gender!
                emptyList(), // TODO source!
//                DragaliaEntity.DATE_TIME_FORMAT.parseUtc(ReleaseDate.substring(0, 10)),
                availabilityMapper(Availability),
                portrait?.url,
                icon?.url,
                skill,
                ability1,
                ability2,
                emptyList(), // TODO attack modifiers
                elementMapper(ElementalType),
                searchElementalResistances(ability1, ability2),
                searchAbilityTypes(ability1, ability2)
            )
        }
    }

    private fun searchAfflictionResistances(
        ability1: DragonAbility,
        ability2: DragonAbility?
    ) = mutableSetOf<Affliction>().apply {
        addAll(ability1.level1.afflictionResistances.keys)
        ability1.level2.afflictionResistances.keys.let { addAll(it) }
        ability2?.level1?.afflictionResistances?.keys?.let { addAll(it) }
        ability2?.level2?.afflictionResistances?.keys?.let { addAll(it) }
    }

    private fun searchElementalResistances(
        ability1: DragonAbility,
        ability2: DragonAbility?
    ) = mutableSetOf<Element>().apply {
        addAll(ability1.level1.elementalResistances.keys)
        ability1.level2.elementalResistances.keys.let { addAll(it) }
        ability2?.level1?.elementalResistances?.keys?.let { addAll(it) }
        ability2?.level2?.elementalResistances?.keys?.let { addAll(it) }
    }

    private fun searchAbilityTypes(
        ability1: DragonAbility,
        ability2: DragonAbility?
    ) = mutableSetOf<AbilityType>().apply {
        add(ability1.level1.abilityType)
        ability1.level2.abilityType.let { add(it) }
        ability2?.level1?.abilityType?.let { add(it) }
        ability2?.level2?.abilityType?.let { add(it) }
    }

    data class Params(
        val dragon: DragonJSON,
        val ability1lvl1: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>,
        val ability1lvl2: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>,
        val ability2lvl1: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>?,
        val ability2lvl2: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>?,
        val skill1: Pair<SkillJSON, Triple<ImageInfoJSON?, ImageInfoJSON?, ImageInfoJSON?>>,
        val icon: ImageInfoJSON?,
        val portrait: ImageInfoJSON?
    )

}
