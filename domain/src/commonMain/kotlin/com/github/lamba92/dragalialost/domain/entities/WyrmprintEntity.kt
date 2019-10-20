package com.github.lamba92.dragalialost.domain.entities

import com.github.lamba92.dragalialost.domain.entities.enums.Availability
import com.github.lamba92.dragalialost.domain.entities.enums.Rarity
import com.github.lamba92.dragalialost.domain.entities.enums.Source
import com.github.lamba92.dragalialost.domain.entities.support.*
import com.soywiz.klock.DateTime

data class WyrmprintEntity(
    override val name: String,
    val description: WyrmprintDescription,
    override val strength: Int,
    override val hp: Int,
    override val baseMinMight: Int,
    override val baseMaxMight: Int,
    override val obtainedFrom: Source,
    override val releaseDate: DateTime,
    override val availability: List<Availability>,
    override val artwork: String,
    val refinedArtwork: String,
    override val rarity: Rarity,
    override val maxLevel: Int,
    val sellValue: SellValue,
    val featuredCharacter: List<String>,
    val afflictionResistances: AfflictionResistances?,
    val elementalResistances: ElementalResistances?,
    val ability1: WyrmprintAbility,
    val ability2: WyrmprintAbility?
) : DragaliaEntity