package com.github.lamba92.dragalialost.domain.entities.support

data class DragonSkill(
    val name: String,
    val skillPointCost: Long,
    val icon: String,
    val level1: SkillLevelData,
    val level2: SkillLevelData
)