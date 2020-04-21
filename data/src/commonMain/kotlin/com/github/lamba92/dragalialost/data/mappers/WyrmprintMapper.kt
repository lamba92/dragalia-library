package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.AbilityGroupJSON
import com.github.lamba92.dragalialost.data.rawresponses.AbilityJSON
import com.github.lamba92.dragalialost.data.rawresponses.ImageInfoJSON
import com.github.lamba92.dragalialost.data.rawresponses.WyrmprintJSON
import com.github.lamba92.dragalialost.domain.entities.DragaliaEntity
import com.github.lamba92.dragalialost.domain.entities.WyrmprintEntity
import com.github.lamba92.dragalialost.domain.entities.enums.AbilityType
import com.github.lamba92.dragalialost.domain.entities.enums.Affliction
import com.github.lamba92.dragalialost.domain.entities.enums.Element
import com.github.lamba92.dragalialost.domain.entities.support.SellValue
import com.github.lamba92.dragalialost.domain.entities.support.WyrmprintAbility
import com.github.lamba92.dragalialost.domain.entities.support.WyrmprintDescription
import com.soywiz.klock.parseUtc

class WyrmprintMapper(
    private val wyrmprintAbilityMapper: WyrmprintAbilityMapper,
    private val sourceMapper: SourceMapper,
    private val availabilityMapper: AvailabilityMapper,
    private val rarityMapper: RarityMapper,
    private val featuredCharacterMapper: FeaturedCharacterMapper
) : SingleFromRemoteMapper<WyrmprintMapper.Params, WyrmprintEntity> {

    override fun fromRemoteSingle(remote: Params) = with(remote) {
        val ability1 = wyrmprintAbilityMapper(
            WyrmprintAbilityMapper.Params(ability1lvl1, ability1lvl2, ability1lvl3)
        )
        val ability2 = if (ability2lvl1 != null && ability2lvl2 != null && ability2lvl3 != null)
            wyrmprintAbilityMapper(WyrmprintAbilityMapper.Params(ability2lvl1, ability2lvl2, ability2lvl3))
        else
            null
        val ability3 = if (ability3lvl1 != null && ability3lvl2 != null && ability3lvl3 != null)
            wyrmprintAbilityMapper(WyrmprintAbilityMapper.Params(ability3lvl1, ability3lvl2, ability3lvl3))
        else
            null

        with(wyrmprint) {
            WyrmprintEntity(
                Name,
                WyrmprintDescription(FlavorText1, FlavorText2, FlavorText3, FlavorText4, FlavorText5),
                MaxAtk.toInt(),
                MaxHp.toInt(),
                MinHp.toInt() + MinAtk.toInt() + ability1.level1.might,
                MaxAtk.toInt() + MaxHp.toInt() + ability1.level3.might +
                        (ability2?.level3?.might ?: 0) + (ability3?.level3?.might ?: 0),
                sourceMapper(Obtain),
                DragaliaEntity.DATE_TIME_FORMAT.parseUtc(ReleaseDate.substring(0, 10)),
                availabilityMapper(Availability),
                portrait1?.url,
                portrait2?.url,
                icon1?.url,
                icon2?.url,
                rarityMapper(Rarity),
                DragaliaEntity.WYRMPRINTS_MAX_LVL,
                SellValue(SellCoin.toInt(), SellDewPoint.toInt()),
                featuredCharacterMapper(FeaturedCharacters),
                searchAfflictionResistances(ability1, ability2, ability3),
                searchElementalResistances(ability1, ability2, ability3),
                ability1,
                ability2,
                ability3,
                searchAbilityTypes(ability1, ability2, ability3)
            )
        }
    }

    private fun searchAfflictionResistances(
        ability1: WyrmprintAbility,
        ability2: WyrmprintAbility?,
        ability3: WyrmprintAbility?
    ) = mutableSetOf<Affliction>().apply {
        addAll(ability1.level1.afflictionResistances.keys)
        addAll(ability1.level2.afflictionResistances.keys)
        addAll(ability1.level3.afflictionResistances.keys)
        ability2?.level1?.afflictionResistances?.keys?.let { addAll(it) }
        ability2?.level2?.afflictionResistances?.keys?.let { addAll(it) }
        ability2?.level3?.afflictionResistances?.keys?.let { addAll(it) }
        ability3?.level1?.afflictionResistances?.keys?.let { addAll(it) }
        ability3?.level2?.afflictionResistances?.keys?.let { addAll(it) }
        ability3?.level3?.afflictionResistances?.keys?.let { addAll(it) }
    }

    private fun searchElementalResistances(
        ability1: WyrmprintAbility,
        ability2: WyrmprintAbility?,
        ability3: WyrmprintAbility?
    ) = mutableSetOf<Element>().apply {
        addAll(ability1.level1.elementalResistances.keys)
        addAll(ability1.level2.elementalResistances.keys)
        addAll(ability1.level3.elementalResistances.keys)
        ability2?.level1?.elementalResistances?.keys?.let { addAll(it) }
        ability2?.level2?.elementalResistances?.keys?.let { addAll(it) }
        ability2?.level3?.elementalResistances?.keys?.let { addAll(it) }
        ability3?.level1?.elementalResistances?.keys?.let { addAll(it) }
        ability3?.level2?.elementalResistances?.keys?.let { addAll(it) }
        ability3?.level3?.elementalResistances?.keys?.let { addAll(it) }
    }

    private fun searchAbilityTypes(
        ability1: WyrmprintAbility,
        ability2: WyrmprintAbility?,
        ability3: WyrmprintAbility?
    ) = mutableSetOf<AbilityType>().apply {
        add(ability1.level1.abilityType)
        ability1.level2.abilityType.let { add(it) }
        ability1.level3.abilityType.let { add(it) }
        ability2?.level1?.abilityType?.let { add(it) }
        ability2?.level2?.abilityType?.let { add(it) }
        ability2?.level3?.abilityType?.let { add(it) }
        ability3?.level1?.abilityType?.let { add(it) }
        ability3?.level2?.abilityType?.let { add(it) }
        ability3?.level3?.abilityType?.let { add(it) }
    }

    data class Params(
        val wyrmprint: WyrmprintJSON,
        val ability1lvl1: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>,
        val ability1lvl2: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>,
        val ability1lvl3: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>,
        val ability2lvl1: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>?,
        val ability2lvl2: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>?,
        val ability2lvl3: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>?,
        val ability3lvl1: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>?,
        val ability3lvl2: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>?,
        val ability3lvl3: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>?,
        val icon1: ImageInfoJSON?,
        val icon2: ImageInfoJSON?,
        val portrait1: ImageInfoJSON?,
        val portrait2: ImageInfoJSON?
    )

}
