package com.github.lamba92.dragalialost.domain.entities.enums

import kotlinx.serialization.Serializable

@Serializable
enum class Affliction {
    POISON, BURN, FREEZE, PARALYSIS,
    BLIND, STUN, CURSE, BOG, SLEEP
}
