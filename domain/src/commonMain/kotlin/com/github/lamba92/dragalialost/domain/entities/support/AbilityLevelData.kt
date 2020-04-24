package com.github.lamba92.dragalialost.domain.entities.support

import com.github.lamba92.dragalialost.domain.entities.enums.AbilityLevel
import com.github.lamba92.dragalialost.domain.entities.enums.AbilityType
import kotlinx.serialization.Serializable

@Serializable
data class AbilityLevelData(
    val description: String,
    val level: AbilityLevel,
    val might: Int,
    val afflictionResistances: AfflictionResistances,
    val elementalResistances: ElementalResistances,
    val abilityType: AbilityType
) {
    override fun toString() =
        "Might $might, type: $abilityType | $description"
}
