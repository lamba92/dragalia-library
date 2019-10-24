package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.AbilityJSON
import com.github.lamba92.dragalialost.data.rawresponses.ImageInfoJSON
import com.github.lamba92.dragalialost.domain.entities.enums.AbilityLevel
import com.github.lamba92.dragalialost.domain.entities.support.AbilityLevelData
import com.github.lamba92.dragalialost.domain.entities.support.DragonAbility

class DragonAbilityMapper : SingleFromRemoteMapper<DragonAbilityMapper.Params, DragonAbility> {
    override fun fromRemoteSingle(remote: Params) = with(remote) {
        DragonAbility(
            a1.first.Name,
            a1.second.url,
            AbilityLevelData(a1.first.Details, AbilityLevel.ONE, a1.first.PartyPowerWeight.toInt()),
            AbilityLevelData(a2.first.Details, AbilityLevel.TWO, a1.first.PartyPowerWeight.toInt())
        )
    }

    data class Params(
        val a1: Pair<AbilityJSON, ImageInfoJSON>,
        val a2: Pair<AbilityJSON, ImageInfoJSON>
    )

}
