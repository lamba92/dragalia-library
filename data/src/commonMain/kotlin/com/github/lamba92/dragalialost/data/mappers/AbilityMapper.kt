package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.AbilityJSON
import com.github.lamba92.dragalialost.domain.entities.enums.AbilityLevel
import com.github.lamba92.dragalialost.domain.entities.support.Ability
import com.github.lamba92.dragalialost.domain.entities.support.AbilityLevelData

class AbilityMapper : SingleFromRemoteMapper<AbilityMapper.Params, Ability> {

    override fun fromRemoteSingle(remote: Params) = with(remote) {
        // TODO ability icon
        Ability(
            lvl1.Name,
            "",
            AbilityLevelData(lvl1.Details, AbilityLevel.ONE, lvl1.PartyPowerWeight.toInt()),
            AbilityLevelData(lvl2.Details, AbilityLevel.TWO, lvl2.PartyPowerWeight.toInt()),
            lvl3?.let { AbilityLevelData(it.Details, AbilityLevel.ONE, it.PartyPowerWeight.toInt()) }
        )
    }

    data class Params(val lvl1: AbilityJSON, val lvl2: AbilityJSON, val lvl3: AbilityJSON?)

}
