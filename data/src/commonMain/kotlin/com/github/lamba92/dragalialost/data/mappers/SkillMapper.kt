package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.SkillJSON
import com.github.lamba92.dragalialost.domain.entities.enums.SkillLevel
import com.github.lamba92.dragalialost.domain.entities.support.Skill
import com.github.lamba92.dragalialost.domain.entities.support.SkillLevelData

class SkillMapper : SingleFromRemoteMapper<SkillJSON, Skill> {

    override fun fromRemoteSingle(remote: SkillJSON) = with(remote) {
        // TODO icon
        // TODO might
        Skill(
            Name,
            Sp.toLong(),
            "",
            SkillLevelData(SkillLevel.ONE, 0, Description1),
            SkillLevelData(SkillLevel.TWO, 0, Description2),
            if (HideLevel3 == "Yes") null else SkillLevelData(SkillLevel.THREE, 0, Description3)
        )
    }

}
