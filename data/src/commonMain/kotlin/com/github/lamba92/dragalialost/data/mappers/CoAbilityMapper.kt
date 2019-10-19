package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.CoAbilityJSON
import com.github.lamba92.dragalialost.domain.entities.enums.CoAbilityLevel
import com.github.lamba92.dragalialost.domain.entities.support.CoAbility
import com.github.lamba92.dragalialost.domain.entities.support.CoAbilityLevelData

class CoAbilityMapper : SingleFromRemoteMapper<CoAbilityMapper.Params, CoAbility> {
    override fun fromRemoteSingle(remote: Params) = with(remote) {
        // TODO icon
        CoAbility(
            lvl1.Name,
            "",
            CoAbilityLevelData(CoAbilityLevel.ONE, lvl1.Details, lvl1.PartyPowerWeight.toInt()),
            CoAbilityLevelData(CoAbilityLevel.ONE, lvl2.Details, lvl2.PartyPowerWeight.toInt()),
            CoAbilityLevelData(CoAbilityLevel.ONE, lvl3.Details, lvl3.PartyPowerWeight.toInt()),
            CoAbilityLevelData(CoAbilityLevel.ONE, lvl4.Details, lvl4.PartyPowerWeight.toInt()),
            CoAbilityLevelData(CoAbilityLevel.ONE, lvl5.Details, lvl5.PartyPowerWeight.toInt())
        )
    }

    data class Params(
        val lvl1: CoAbilityJSON,
        val lvl2: CoAbilityJSON,
        val lvl3: CoAbilityJSON,
        val lvl4: CoAbilityJSON,
        val lvl5: CoAbilityJSON
    )

}
