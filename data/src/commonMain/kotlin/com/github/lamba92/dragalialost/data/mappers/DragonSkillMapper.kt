package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.ImageInfoJSON
import com.github.lamba92.dragalialost.data.rawresponses.SkillJSON
import com.github.lamba92.dragalialost.data.utils.sanitize
import com.github.lamba92.dragalialost.domain.entities.DragaliaEntity
import com.github.lamba92.dragalialost.domain.entities.enums.SkillLevel
import com.github.lamba92.dragalialost.domain.entities.support.DragonSkill
import com.github.lamba92.dragalialost.domain.entities.support.SkillLevelData

class DragonSkillMapper :
    SingleFromRemoteMapper<Pair<SkillJSON, Triple<ImageInfoJSON, ImageInfoJSON, ImageInfoJSON>>, DragonSkill> {

    override fun fromRemoteSingle(remote: Pair<SkillJSON, Triple<ImageInfoJSON, ImageInfoJSON, ImageInfoJSON>>) =
        with(remote) {
            DragonSkill(
                first.Name,
                first.Sp.toLong(),
                second.first.url,
                SkillLevelData(SkillLevel.ONE, DragaliaEntity.DRAGON_SKILL_LIL1_MIGHT, first.Description1.sanitize()),
                SkillLevelData(SkillLevel.TWO, DragaliaEntity.DRAGON_SKILL_LVL2_MIGHT, first.Description2.sanitize())
            )
        }

}
