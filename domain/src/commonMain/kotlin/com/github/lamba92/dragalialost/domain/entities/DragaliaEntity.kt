package com.github.lamba92.dragalialost.domain.entities

import com.github.lamba92.dragalialost.domain.entities.enums.Availability
import com.github.lamba92.dragalialost.domain.entities.enums.Rarity
import com.github.lamba92.dragalialost.domain.entities.enums.Source
import com.soywiz.klock.DateTime

interface DragaliaEntity {
    val name: String
    val maxLevel: Int
    val hp: Int
    val strength: Int
    val baseMinMight: Int
    val baseMaxMight: Int
    val rarity: Rarity
    val obtainedFrom: Source
    val releaseDate: DateTime
    val availability: Availability
    val artwork: String
}
