package com.github.lamba92.dragalia.entities.support

import com.github.lamba92.dragalia.entities.enums.WyrmprintAbilityType

data class WyrmprintAbility(
    override val name: String,
    override val description: String,
    override val level: AbilityLevel,
    override val might: Int,
    override val artwork: String,
    val type: WyrmprintAbilityType
) : Ability