package com.github.lamba92.dragalialost.domain.entities.support

import com.github.lamba92.dragalialost.domain.entities.enums.AbilityLevel

data class AbilityLevelData(
    val descriptor: String,
    val level: AbilityLevel,
    val might: Int
)