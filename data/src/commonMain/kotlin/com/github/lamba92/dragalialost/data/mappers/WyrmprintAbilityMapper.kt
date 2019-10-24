package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.AbilityJSON
import com.github.lamba92.dragalialost.data.rawresponses.ImageInfoJSON
import com.github.lamba92.dragalialost.domain.entities.enums.AbilityLevel
import com.github.lamba92.dragalialost.domain.entities.enums.WyrmprintAbilityType
import com.github.lamba92.dragalialost.domain.entities.support.AbilityLevelData
import com.github.lamba92.dragalialost.domain.entities.support.WyrmprintAbility

class WyrmprintAbilityMapper : SingleFromRemoteMapper<WyrmprintAbilityMapper.Params, WyrmprintAbility> {

    override fun fromRemoteSingle(remote: Params) = with(remote) {
        WyrmprintAbility(
            lvl1.first.Name,
            lvl1.second.url,
            WyrmprintAbilityType.OTHER, // TODO
            AbilityLevelData(lvl1.first.Details, AbilityLevel.ONE, lvl1.first.PartyPowerWeight.toInt()),
            AbilityLevelData(lvl2.first.Details, AbilityLevel.TWO, lvl2.first.PartyPowerWeight.toInt()),
            AbilityLevelData(lvl3.first.Details, AbilityLevel.THREE, lvl3.first.PartyPowerWeight.toInt())
        )
    }

    data class Params(
        val lvl1: Pair<AbilityJSON, ImageInfoJSON>,
        val lvl2: Pair<AbilityJSON, ImageInfoJSON>,
        val lvl3: Pair<AbilityJSON, ImageInfoJSON>
    )

}
