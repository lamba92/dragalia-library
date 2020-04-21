package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.AbilityGroupJSON
import com.github.lamba92.dragalialost.data.rawresponses.AbilityJSON
import com.github.lamba92.dragalialost.data.rawresponses.ImageInfoJSON
import com.github.lamba92.dragalialost.data.utils.sanitize
import com.github.lamba92.dragalialost.domain.entities.enums.AbilityLevel
import com.github.lamba92.dragalialost.domain.entities.support.AbilityLevelData
import com.github.lamba92.dragalialost.domain.entities.support.WyrmprintAbility

class WyrmprintAbilityMapper(
    private val elementalResistancesMapper: ElementalResistancesMapper,
    private val afflictionResistancesMapper: AfflictionResistancesMapper,
    private val abilityTypeMapper: AbilityTypeMapper
) : SingleFromRemoteMapper<WyrmprintAbilityMapper.Params, WyrmprintAbility> {

    override fun fromRemoteSingle(remote: Params) = with(remote) {
        WyrmprintAbility(
            lvl1.first.GenericName,
            lvl1.second?.url,
            AbilityLevelData(
                lvl1.first.Details.sanitize(),
                AbilityLevel.ONE,
                lvl1.first.PartyPowerWeight.toInt(),
                afflictionResistancesMapper(lvl1.first),
                elementalResistancesMapper(lvl1.first),
                abilityTypeMapper(lvl1.third)
            ),
            AbilityLevelData(
                lvl2.first.Details.sanitize(), AbilityLevel.TWO, lvl2.first.PartyPowerWeight.toInt(),
                afflictionResistancesMapper(lvl2.first),
                elementalResistancesMapper(lvl2.first),
                abilityTypeMapper(lvl2.third)
            ),
            AbilityLevelData(
                lvl3.first.Details.sanitize(), AbilityLevel.THREE, lvl3.first.PartyPowerWeight.toInt(),
                afflictionResistancesMapper(lvl3.first),
                elementalResistancesMapper(lvl3.first),
                abilityTypeMapper(lvl1.third)
            )
        )
    }

    data class Params(
        val lvl1: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>,
        val lvl2: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>,
        val lvl3: Triple<AbilityJSON, ImageInfoJSON?, AbilityGroupJSON>
    )

}
