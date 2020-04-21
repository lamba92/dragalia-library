package com.github.lamba92.dragalialost.domain.entities.support

import com.github.lamba92.dragalialost.domain.utils.appendln

data class CoAbility(
    val name: String,
    val icon: String?,
    val level1: CoAbilityLevelData,
    val level2: CoAbilityLevelData,
    val level3: CoAbilityLevelData,
    val level4: CoAbilityLevelData,
    val level5: CoAbilityLevelData
) {
    override fun toString() = buildString {
        appendln("   • $name")
        appendln("   • $icon")
        appendln("     • LVL1: $level1")
        appendln("     • LVL2: $level2")
        appendln("     • LVL3: $level3")
        appendln("     • LVL4: $level4")
        appendln("     • LVL5: $level5")
    }
}
