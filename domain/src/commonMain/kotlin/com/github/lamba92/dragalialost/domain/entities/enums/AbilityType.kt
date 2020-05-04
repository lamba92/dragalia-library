package com.github.lamba92.dragalialost.domain.entities.enums

import kotlinx.serialization.Serializable

@Serializable
enum class AbilityType {
    ATTACK, DEFENSE, RECOVERY,
    SUPPORT, EVENT_PERKS, OTHER,
    ANTI_CLASS, AFFLICTIONS_RESISTANCE,
    ELEMENT_RESISTANCE, CLASS_BANES
}
