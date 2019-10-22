package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.AbilityJSON
import com.github.lamba92.dragalialost.domain.entities.enums.AbilityLevel
import com.github.lamba92.dragalialost.domain.entities.support.AbilityLevelData
import com.github.lamba92.dragalialost.domain.entities.support.AdventurerAbility

class AbilityMapper : SingleFromRemoteMapper<AbilityMapper.Params, AdventurerAbility> {

    override fun fromRemoteSingle(remote: Params) = with(remote) {
        // TODO ability icon
        AdventurerAbility(
            lvl1.Name,
            "",
            AbilityLevelData(lvl1.Details.replace("'''", ""), AbilityLevel.ONE, lvl1.PartyPowerWeight.toInt()),
            lvl2?.let {
                AbilityLevelData(
                    it.Details.replace("'''", ""),
                    AbilityLevel.TWO,
                    it.PartyPowerWeight.toInt()
                )
            },
            lvl3?.let { AbilityLevelData(it.Details.replace("'''", ""), AbilityLevel.ONE, it.PartyPowerWeight.toInt()) }
        )
    }

    data class Params(val lvl1: AbilityJSON, val lvl2: AbilityJSON?, val lvl3: AbilityJSON?)

}
