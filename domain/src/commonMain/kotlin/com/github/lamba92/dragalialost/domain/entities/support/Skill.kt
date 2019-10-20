package com.github.lamba92.dragalialost.domain.entities.support


data class Skill(
    val name: String,
    val skillPointCost: Long,
    val icon: String,
    val level1: SkillLevelData,
    val level2: SkillLevelData,
    val level3: SkillLevelData? = null
) {
    companion object {
        const val LVL1_MIGHT = 100
        const val LVL2_MIGHT = 200
        const val LVL3_MIGHT = 300
    }
}