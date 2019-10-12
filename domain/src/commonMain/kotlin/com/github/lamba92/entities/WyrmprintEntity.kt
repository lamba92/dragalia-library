package com.github.lamba92.entities

import com.github.lamba92.entities.enums.Availability
import com.github.lamba92.entities.enums.Rarity
import com.github.lamba92.entities.enums.Source
import com.github.lamba92.entities.support.AfflictionResistances
import com.github.lamba92.entities.support.ElementalResistances
import com.github.lamba92.entities.support.SellValue
import com.github.lamba92.entities.support.WyrmprintAbility
import com.soywiz.klock.DateTime

data class WyrmprintEntity(
    override val name: String,
    override val description: String,
    override val strength: Int,
    override val hp: Int,
    override val baseMinMight: Int,
    override val baseMaxMight: Int,
    override val obtainedFrom: Source,
    override val releaseDate: DateTime,
    override val availability: Availability,
    override val artwork: String,
    override val rarity: Rarity,
    override val level: Int,
    val sellValue: SellValue,
    val featuredCharacter: List<String>,
    val afflictionResistances: AfflictionResistances,
    val elementalResistances: ElementalResistances,
    val isMaxUnbound: Boolean,
    val ability1: WyrmprintAbility,
    val ability2: WyrmprintAbility?
) : DragaliaEntity