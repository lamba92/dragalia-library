package com.github.lamba92.dragalialost.domain.entities.support

import com.github.lamba92.dragalialost.domain.utils.appendln
import kotlinx.serialization.Serializable

@Serializable
data class AdventurerAbility(
    val name: String,
    val icon: String?,
    val level1: AbilityLevelData,
    val level2: AbilityLevelData?,
    val level3: AbilityLevelData?
) {
    override fun toString() = buildString {
        appendln("   • $name")
        appendln("   • $icon")
        appendln("     • LVL1: $level1")
        level2?.let { appendln("     • LVL2: $it") }
        level3?.let { appendln("     • LVL3: $it") }
    }
}
