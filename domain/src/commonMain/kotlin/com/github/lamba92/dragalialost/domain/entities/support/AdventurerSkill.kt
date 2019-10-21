package com.github.lamba92.dragalialost.domain.entities.support


data class AdventurerSkill(
    val name: String,
    val skillPointCost: Long,
    val icon: String,
    val level1: SkillLevelData,
    val level2: SkillLevelData,
    val level3: SkillLevelData? = null
)