package com.github.lamba92.entities.support

import com.github.lamba92.entities.enums.ResistancePercentage

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
)
