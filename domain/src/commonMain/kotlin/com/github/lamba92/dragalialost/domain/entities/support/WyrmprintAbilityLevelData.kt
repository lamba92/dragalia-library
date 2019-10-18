package com.github.lamba92.dragalialost.domain.entities.support

import com.github.lamba92.dragalialost.domain.entities.enums.WyrmprintAbilityLevel

data class WyrmprintAbilityLevelData(
    val descriptor: String,
    val level: WyrmprintAbilityLevel,
    val might: Int
)