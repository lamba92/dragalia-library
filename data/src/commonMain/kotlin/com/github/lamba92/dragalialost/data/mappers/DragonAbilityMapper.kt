package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.AbilityJSON
import com.github.lamba92.dragalialost.domain.entities.enums.AbilityLevel
import com.github.lamba92.dragalialost.domain.entities.support.AbilityLevelData
import com.github.lamba92.dragalialost.domain.entities.support.DragonAbility

class DragonAbilityMapper : SingleFromRemoteMapper<Pair<AbilityJSON, AbilityJSON>, DragonAbility> {
    override fun fromRemoteSingle(remote: Pair<AbilityJSON, AbilityJSON>) = with(remote) {
        // TODO icon
        DragonAbility(
            first.Name,
            "",
            AbilityLevelData(first.Details, AbilityLevel.ONE, first.PartyPowerWeight.toInt()),
            AbilityLevelData(second.Details, AbilityLevel.TWO, second.PartyPowerWeight.toInt())
        )
    }
}
