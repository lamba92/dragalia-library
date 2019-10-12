package com.github.lamba92.entities.support

interface Ability {
    val name: String
    val description: String
    val level: AbilityLevel
    val might: Int
    val artwork: String
}