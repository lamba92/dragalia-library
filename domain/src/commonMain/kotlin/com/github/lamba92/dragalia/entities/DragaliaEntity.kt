package com.github.lamba92.dragalia.entities

import com.github.lamba92.dragalia.entities.enums.Availability
import com.github.lamba92.dragalia.entities.enums.Rarity
import com.github.lamba92.dragalia.entities.enums.Source
import com.soywiz.klock.DateTime

interface DragaliaEntity {
    val name: String
    val description: String
    val level: Int
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
