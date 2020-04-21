package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.CoAbilityJSON
import com.github.lamba92.dragalialost.data.rawresponses.ImageInfoJSON
import com.github.lamba92.dragalialost.data.utils.sanitize
import com.github.lamba92.dragalialost.domain.entities.enums.CoAbilityLevel
import com.github.lamba92.dragalialost.domain.entities.support.CoAbility
import com.github.lamba92.dragalialost.domain.entities.support.CoAbilityLevelData

class CoAbilityMapper : SingleFromRemoteMapper<CoAbilityMapper.Params, CoAbility> {
    override fun fromRemoteSingle(remote: Params) = with(remote) {
        CoAbility(
            lvl1.first.GenericName,
            lvl1.second?.url,
            CoAbilityLevelData(
                CoAbilityLevel.ONE,
                lvl1.first.Details.sanitize(),
                lvl1.first.PartyPowerWeight.toInt()
            ),
            CoAbilityLevelData(
                CoAbilityLevel.TWO,
                lvl2.first.Details.sanitize(),
                lvl2.first.PartyPowerWeight.toInt()
            ),
            CoAbilityLevelData(
                CoAbilityLevel.THREE,
                lvl3.first.Details.sanitize(),
                lvl3.first.PartyPowerWeight.toInt()
            ),
            CoAbilityLevelData(
                CoAbilityLevel.FOUR,
                lvl4.first.Details.sanitize(),
                lvl4.first.PartyPowerWeight.toInt()
            ),
            CoAbilityLevelData(
                CoAbilityLevel.FIVE,
                lvl5.first.Details.sanitize(),
                lvl5.first.PartyPowerWeight.toInt()
            )
        )
    }

    data class Params(
        val lvl1: Pair<CoAbilityJSON, ImageInfoJSON?>,
        val lvl2: Pair<CoAbilityJSON, ImageInfoJSON?>,
        val lvl3: Pair<CoAbilityJSON, ImageInfoJSON?>,
        val lvl4: Pair<CoAbilityJSON, ImageInfoJSON?>,
        val lvl5: Pair<CoAbilityJSON, ImageInfoJSON?>
    )

}
