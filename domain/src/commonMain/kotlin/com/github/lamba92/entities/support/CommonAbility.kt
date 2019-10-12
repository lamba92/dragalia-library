package com.github.lamba92.entities.support

data class CommonAbility(
    override val name: String,
    override val description: String,
    override val level: AbilityLevel,
    override val might: Int,
    override val artwork: String
) : Ability

