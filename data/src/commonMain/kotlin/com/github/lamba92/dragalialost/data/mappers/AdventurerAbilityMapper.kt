package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.AbilityJSON
import com.github.lamba92.dragalialost.data.rawresponses.ImageInfoJSON
import com.github.lamba92.dragalialost.data.utils.sanitize
import com.github.lamba92.dragalialost.domain.entities.enums.AbilityLevel
import com.github.lamba92.dragalialost.domain.entities.support.AbilityLevelData
import com.github.lamba92.dragalialost.domain.entities.support.AdventurerAbility

class AdventurerAbilityMapper : SingleFromRemoteMapper<AdventurerAbilityMapper.Params, AdventurerAbility> {

    override fun fromRemoteSingle(remote: Params) = with(remote) {
        AdventurerAbility(
            lvl1.first.Name,
            lvl1.second.url,
            AbilityLevelData(
                lvl1.first.Details.sanitize(),
                AbilityLevel.ONE,
                lvl1.first.PartyPowerWeight.toInt()
            ),
            lvl2?.first?.let {
                AbilityLevelData(
                    it.Details.sanitize(),
                    AbilityLevel.TWO,
                    it.PartyPowerWeight.toInt()
                )
            },
            lvl3?.first?.let {
                AbilityLevelData(
                    it.Details.sanitize(),
                    AbilityLevel.THREE,
                    it.PartyPowerWeight.toInt()
                )
            }
        )
    }

    data class Params(
        val lvl1: Pair<AbilityJSON, ImageInfoJSON>,
        val lvl2: Pair<AbilityJSON, ImageInfoJSON>?,
        val lvl3: Pair<AbilityJSON, ImageInfoJSON>?
    )

}
