package com.github.lamba92.dragalialost.domain.entities.support

data class AdventurerAbility(
    val name: String,
    val icon: String,
    val level1: AbilityLevelData,
    val level2: AbilityLevelData?,
    val level3: AbilityLevelData?
)