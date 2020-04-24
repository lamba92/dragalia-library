package com.github.lamba92.dragalialost.domain.entities.support

import kotlinx.serialization.Serializable

@Serializable
data class ManaCircleBonusStats(
    val circle1: Int,
    val circle2: Int,
    val circle3: Int,
    val circle4: Int,
    val circle5: Int,
    val finalBonus: Int
)
