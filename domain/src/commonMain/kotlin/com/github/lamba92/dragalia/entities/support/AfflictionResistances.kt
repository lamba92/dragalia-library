package com.github.lamba92.dragalia.entities.support

import com.github.lamba92.dragalia.entities.enums.Afflictions
import com.github.lamba92.dragalia.entities.enums.Afflictions.*
import com.github.lamba92.dragalia.entities.enums.ResistancePercentage

data class AfflictionResistances(
    val poison: ResistancePercentage,
    val burn: ResistancePercentage,
    val freeze: ResistancePercentage,
    val paralysis: ResistancePercentage,
    val blind: ResistancePercentage,
    val stun: ResistancePercentage,
    val curse: ResistancePercentage,
    val bog: ResistancePercentage,
    val sleep: ResistancePercentage
) {
    operator fun get(affliction: Afflictions) = when (affliction) {
        POISON -> poison
        BURN -> burn
        FREEZE -> freeze
        PARALYSIS -> paralysis
        BLIND -> blind
        STUN -> stun
        CURSE -> curse
        BOG -> bog
        SLEEP -> sleep
    }
}
