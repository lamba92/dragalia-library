package com.github.lamba92.dragalialost.domain.entities.support

import com.github.lamba92.dragalialost.domain.entities.enums.WyrmprintAbilityType

data class WyrmprintAbility(
    val name: String,
    val icon: String,
    val type: WyrmprintAbilityType,
    val level1: AbilityLevelData,
    val level2: AbilityLevelData,
    val level3: AbilityLevelData
)