package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.AbilityGroupJSON
import com.github.lamba92.dragalialost.data.rawresponses.AbilityJSON
import com.github.lamba92.dragalialost.data.rawresponses.ImageInfoJSON
import com.github.lamba92.dragalialost.data.utils.sanitize
import com.github.lamba92.dragalialost.domain.entities.enums.AbilityLevel
import com.github.lamba92.dragalialost.domain.entities.support.AbilityLevelData
import com.github.lamba92.dragalialost.domain.entities.support.DragonAbility

class DragonAbilityMapper(
    private val elementalResistancesMapper: ElementalResistancesMapper,
    private val afflictionResistancesMapper: AfflictionResistancesMapper,
    private val abilityTypeMapper: AbilityTypeMapper
) : SingleFromRemoteMapper<DragonAbilityMapper.Params, DragonAbility> {
    override fun fromRemoteSingle(remote: Params) = with(remote) {
        DragonAbility(
            a1.first.GenericName,
            a1.second.url,
            AbilityLevelData(
                a1.first.Details.sanitize(),
                AbilityLevel.ONE,
                a1.first.PartyPowerWeight.toInt(),
                afflictionResistancesMapper(a1.first),
                elementalResistancesMapper(a1.first),
                abilityTypeMapper(a1.third)
            ),
            AbilityLevelData(
                a2.first.Details.sanitize(),
                AbilityLevel.TWO,
                a1.first.PartyPowerWeight.toInt(),
                afflictionResistancesMapper(a2.first),
                elementalResistancesMapper(a2.first),
                abilityTypeMapper(a2.third)
            )
        )
    }

    data class Params(
        val a1: Triple<AbilityJSON, ImageInfoJSON, AbilityGroupJSON>,
        val a2: Triple<AbilityJSON, ImageInfoJSON, AbilityGroupJSON>
    )

}
