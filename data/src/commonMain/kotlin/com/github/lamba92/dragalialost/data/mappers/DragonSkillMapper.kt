package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.SkillJSON
import com.github.lamba92.dragalialost.domain.entities.DragaliaEntity
import com.github.lamba92.dragalialost.domain.entities.enums.SkillLevel
import com.github.lamba92.dragalialost.domain.entities.support.DragonSkill
import com.github.lamba92.dragalialost.domain.entities.support.SkillLevelData

class DragonSkillMapper : SingleFromRemoteMapper<SkillJSON, DragonSkill> {

    override fun fromRemoteSingle(remote: SkillJSON) = with(remote) {
        // TODO icon
        DragonSkill(
            Name,
            Sp.toLong(),
            "",
            SkillLevelData(SkillLevel.ONE, DragaliaEntity.SKILL_LVL1_MIGHT, Description1),
            SkillLevelData(SkillLevel.TWO, DragaliaEntity.SKILL_LVL2_MIGHT, Description2)
        )
    }

}
