package com.github.lamba92.dragalialost.domain.entities.support

import com.github.lamba92.dragalialost.domain.utils.appendln

data class DragonAbility(
    val name: String,
    val icon: String,
    val level1: AbilityLevelData,
    val level2: AbilityLevelData
) {
    override fun toString() = buildString {
        appendln("   • $name")
        appendln("   • $icon")
        appendln("     • LVL1: $level1")
        appendln("     • LVL2: $level2")
    }
}