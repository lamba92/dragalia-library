package com.github.lamba92.entities.support

import com.github.lamba92.entities.enums.SkillLevel

data class Skill(
    val description: String,
    val skillPointCost: Int,
    val level: SkillLevel,
    val might: Int,
    val artwork: String
)