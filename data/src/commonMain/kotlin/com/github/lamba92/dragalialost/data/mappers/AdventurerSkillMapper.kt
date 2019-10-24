package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.ImageInfoJSON
import com.github.lamba92.dragalialost.data.rawresponses.SkillJSON
import com.github.lamba92.dragalialost.data.utils.sanitize
import com.github.lamba92.dragalialost.domain.entities.DragaliaEntity.Companion.ADVENTURER_SKILL_LIL1_MIGHT
import com.github.lamba92.dragalialost.domain.entities.DragaliaEntity.Companion.ADVENTURER_SKILL_LVL2_MIGHT
import com.github.lamba92.dragalialost.domain.entities.DragaliaEntity.Companion.ADVENTURER_SKILL_LVL3_MIGHT
import com.github.lamba92.dragalialost.domain.entities.enums.SkillLevel
import com.github.lamba92.dragalialost.domain.entities.support.AdventurerSkill
import com.github.lamba92.dragalialost.domain.entities.support.SkillLevelData

class AdventurerSkillMapper :
    SingleFromRemoteMapper<Pair<SkillJSON, Triple<ImageInfoJSON, ImageInfoJSON, ImageInfoJSON>>, AdventurerSkill> {

    override fun fromRemoteSingle(remote: Pair<SkillJSON, Triple<ImageInfoJSON, ImageInfoJSON, ImageInfoJSON>>) =
        with(remote) {
        AdventurerSkill(
            first.Name,
            first.Sp.toLong(),
            second.first.url,
            SkillLevelData(SkillLevel.ONE, ADVENTURER_SKILL_LIL1_MIGHT, first.Description1.sanitize()),
            SkillLevelData(SkillLevel.TWO, ADVENTURER_SKILL_LVL2_MIGHT, first.Description2.sanitize()),
            if (first.HideLevel3 == "Yes" || first.HideLevel3 == "1") null else SkillLevelData(
                SkillLevel.THREE,
                ADVENTURER_SKILL_LVL3_MIGHT,
                first.Description3.sanitize()
            )
        )
    }

}