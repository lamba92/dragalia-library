package com.github.lamba92.dragalialost.domain.entities.support

import com.github.lamba92.dragalialost.domain.utils.appendln


data class AdventurerSkill(
    val name: String,
    val skillPointCost: Long,
    val icon: String,
    val level1: SkillLevelData,
    val level2: SkillLevelData,
    val level3: SkillLevelData? = null
) {
    override fun toString() = buildString {
        appendln("   • $name")
        appendln("   • SP cost: $skillPointCost")
        appendln("   • $icon")
        appendln("     • LVL1: $level1")
        appendln("     • LVL2: $level2")
        level3?.let { appendln("     • LVL3: $it") }
    }
}