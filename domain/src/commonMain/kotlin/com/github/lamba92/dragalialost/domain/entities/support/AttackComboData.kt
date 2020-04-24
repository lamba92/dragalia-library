package com.github.lamba92.dragalialost.domain.entities.support

import com.github.lamba92.dragalialost.domain.entities.enums.AttackComboNumber
import kotlinx.serialization.Serializable

@Serializable
data class AttackComboData(
    val attackCombo: AttackComboNumber,
    val damageMultiplier: Int,
    val hitCount: Int
)
