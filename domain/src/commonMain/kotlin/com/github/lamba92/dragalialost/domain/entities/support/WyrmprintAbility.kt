package com.github.lamba92.dragalialost.domain.entities.support

data class WyrmprintAbility(
    val name: String,
    val icon: String,
    val level1: WyrmprintAbilityLevelData,
    val level2: WyrmprintAbilityLevelData,
    val level3: WyrmprintAbilityLevelData
)