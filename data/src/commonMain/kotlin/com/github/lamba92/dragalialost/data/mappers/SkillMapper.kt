package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.SkillJSON
import com.github.lamba92.dragalialost.domain.entities.enums.SkillLevel
import com.github.lamba92.dragalialost.domain.entities.support.Skill
import com.github.lamba92.dragalialost.domain.entities.support.Skill.Companion.LVL1_MIGHT
import com.github.lamba92.dragalialost.domain.entities.support.Skill.Companion.LVL2_MIGHT
import com.github.lamba92.dragalialost.domain.entities.support.Skill.Companion.LVL3_MIGHT
import com.github.lamba92.dragalialost.domain.entities.support.SkillLevelData

class SkillMapper : SingleFromRemoteMapper<SkillJSON, Skill> {

    override fun fromRemoteSingle(remote: SkillJSON) = with(remote) {
        // TODO icon
        Skill(
            Name,
            Sp.toLong(),
            "",
            SkillLevelData(SkillLevel.ONE, LVL1_MIGHT, Description1),
            SkillLevelData(SkillLevel.TWO, LVL2_MIGHT, Description2),
            if (HideLevel3 == "Yes") null else SkillLevelData(SkillLevel.THREE, LVL3_MIGHT, Description3)
        )
    }

}
