package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.SkillJSON
import com.github.lamba92.dragalialost.domain.entities.DragaliaEntity.Companion.SKILL_LVL1_MIGHT
import com.github.lamba92.dragalialost.domain.entities.DragaliaEntity.Companion.SKILL_LVL2_MIGHT
import com.github.lamba92.dragalialost.domain.entities.DragaliaEntity.Companion.SKILL_LVL3_MIGHT
import com.github.lamba92.dragalialost.domain.entities.enums.SkillLevel
import com.github.lamba92.dragalialost.domain.entities.support.AdventurerSkill
import com.github.lamba92.dragalialost.domain.entities.support.SkillLevelData

class AdventurerSkillMapper : SingleFromRemoteMapper<SkillJSON, AdventurerSkill> {

    override fun fromRemoteSingle(remote: SkillJSON) = with(remote) {
        // TODO icon
        AdventurerSkill(
            Name,
            Sp.toLong(),
            "",
            SkillLevelData(SkillLevel.ONE, SKILL_LVL1_MIGHT, Description1),
            SkillLevelData(SkillLevel.TWO, SKILL_LVL2_MIGHT, Description2),
            if (HideLevel3 == "Yes") null else SkillLevelData(SkillLevel.THREE, SKILL_LVL3_MIGHT, Description3)
        )
    }

}
