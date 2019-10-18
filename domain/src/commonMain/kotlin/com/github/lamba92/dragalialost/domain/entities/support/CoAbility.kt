package com.github.lamba92.dragalialost.domain.entities.support

import com.github.lamba92.dragalialost.domain.entities.enums.CoAbilityLevel

data class CoAbility(
    val name: String,
    val icon: String,
    val level1: CoAbilityLevelData,
    val level2: CoAbilityLevelData,
    val level3: CoAbilityLevelData,
    val level4: CoAbilityLevelData,
    val level5: CoAbilityLevelData
)

data class CoAbilityLevelData(
    val level: CoAbilityLevel,
    val description: String,
    val might: Int
)