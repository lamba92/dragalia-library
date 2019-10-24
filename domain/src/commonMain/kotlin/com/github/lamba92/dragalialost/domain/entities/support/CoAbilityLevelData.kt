package com.github.lamba92.dragalialost.domain.entities.support

import com.github.lamba92.dragalialost.domain.entities.enums.CoAbilityLevel

data class CoAbilityLevelData(
    val level: CoAbilityLevel,
    val description: String,
    val might: Int
) {
    override fun toString() =
        "Might $might | $description"

}