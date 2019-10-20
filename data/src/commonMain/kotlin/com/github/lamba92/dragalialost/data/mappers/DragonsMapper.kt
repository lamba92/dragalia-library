package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.AbilityJSON
import com.github.lamba92.dragalialost.data.rawresponses.DragonJSON
import com.github.lamba92.dragalialost.data.rawresponses.SkillJSON
import com.github.lamba92.dragalialost.domain.entities.DragonEntity


class DragonsMapper(
    private val dragonAbilityMapper: DragonAbilityMapper
) : SingleFromRemoteMapper<DragonsMapper.Params, DragonEntity> {

    override fun fromRemoteSingle(remote: Params) = with(remote) {
        TODO()
    }

    data class Params(
        val dragon: DragonJSON,
        val ability1lvl2: AbilityJSON?,
        val ability1lvl1: AbilityJSON?,
        val ability1lvl3: AbilityJSON?,
        val ability2lvl1: AbilityJSON?,
        val ability2lvl2: AbilityJSON?,
        val ability2lvl3: AbilityJSON?,
        val skill1: SkillJSON
    )

}
