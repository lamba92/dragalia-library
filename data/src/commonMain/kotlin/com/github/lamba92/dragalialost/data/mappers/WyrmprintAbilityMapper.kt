package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.AbilityJSON
import com.github.lamba92.dragalialost.domain.entities.enums.AbilityLevel
import com.github.lamba92.dragalialost.domain.entities.enums.WyrmprintAbilityType
import com.github.lamba92.dragalialost.domain.entities.support.AbilityLevelData
import com.github.lamba92.dragalialost.domain.entities.support.WyrmprintAbility

class WyrmprintAbilityMapper : SingleFromRemoteMapper<WyrmprintAbilityMapper.Params, WyrmprintAbility> {

    override fun fromRemoteSingle(remote: Params) = with(remote) {
        WyrmprintAbility(
            lvl1.Name,
            "", // TODO icon!
            WyrmprintAbilityType.OTHER, // TODO
            AbilityLevelData(lvl1.Details, AbilityLevel.ONE, lvl1.PartyPowerWeight.toInt()),
            AbilityLevelData(lvl2.Details, AbilityLevel.TWO, lvl2.PartyPowerWeight.toInt()),
            AbilityLevelData(lvl3.Details, AbilityLevel.THREE, lvl3.PartyPowerWeight.toInt())
        )
    }

    data class Params(
        val lvl1: AbilityJSON,
        val lvl2: AbilityJSON,
        val lvl3: AbilityJSON
    )

}
