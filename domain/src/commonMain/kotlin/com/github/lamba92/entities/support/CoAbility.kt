package com.github.lamba92.entities.support

import com.github.lamba92.entities.enums.CoAbilityLevel

data class CoAbility(
    val level: CoAbilityLevel,
    val description: String,
    val might: Int
)