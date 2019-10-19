package com.github.lamba92.dragalialost.data.rawresponses

import kotlinx.serialization.Serializable

@Serializable
data class SkillIdCargoJSON(val cargoquery: List<SkillIdNestedTitleJson>)

@Serializable
data class SkillIdNestedTitleJson(val title: SkillIdJSON)

@Serializable
data class SkillIdJSON(
    val SkillId: String
)