package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.AbilityGroupJSON
import com.github.lamba92.dragalialost.data.rawresponses.AbilityJSON
import com.github.lamba92.dragalialost.data.rawresponses.ImageInfoJSON
import com.github.lamba92.dragalialost.data.utils.sanitize
import com.github.lamba92.dragalialost.domain.entities.enums.AbilityLevel
import com.github.lamba92.dragalialost.domain.entities.support.AbilityLevelData
import com.github.lamba92.dragalialost.domain.entities.support.AdventurerAbility

class AdventurerAbilityMapper(
    private val elementalResistancesMapper: ElementalResistancesMapper,
    private val afflictionResistancesMapper: AfflictionResistancesMapper
) : SingleFromRemoteMapper<AdventurerAbilityMapper.Params, AdventurerAbility> {

    override fun fromRemoteSingle(remote: Params) = with(remote) {
        AdventurerAbility(
            lvl1.first.GenericName,
            lvl1.second.url,
            AbilityLevelData(
                lvl1.first.Details.sanitize(),
                AbilityLevel.ONE,
                lvl1.first.PartyPowerWeight.toInt(),
                afflictionResistancesMapper(AfflictionResistancesMapper.Params(lvl1.first, lvl1.third)),
                elementalResistancesMapper(ElementalResistancesMapper.Params(lvl1.first, lvl1.third))
            ),
            lvl2?.let {
                AbilityLevelData(
                    it.first.Details.sanitize(),
                    AbilityLevel.TWO,
                    it.first.PartyPowerWeight.toInt(),
                    afflictionResistancesMapper(AfflictionResistancesMapper.Params(it.first, it.third)),
                    elementalResistancesMapper(ElementalResistancesMapper.Params(it.first, it.third))
                )
            },
            lvl3?.let {
                AbilityLevelData(
                    it.first.Details.sanitize(),
                    AbilityLevel.THREE,
                    it.first.PartyPowerWeight.toInt(),
                    afflictionResistancesMapper(AfflictionResistancesMapper.Params(it.first, it.third)),
                    elementalResistancesMapper(ElementalResistancesMapper.Params(it.first, it.third))
                )
            }
        )
    }

    data class Params(
        val lvl1: Triple<AbilityJSON, ImageInfoJSON, AbilityGroupJSON>,
        val lvl2: Triple<AbilityJSON, ImageInfoJSON, AbilityGroupJSON>?,
        val lvl3: Triple<AbilityJSON, ImageInfoJSON, AbilityGroupJSON>?
    )

}
