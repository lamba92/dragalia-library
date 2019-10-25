package com.github.lamba92.dragalialost.domain.entities

import com.github.lamba92.dragalialost.domain.entities.enums.*
import com.github.lamba92.dragalialost.domain.entities.support.SellValue
import com.github.lamba92.dragalialost.domain.entities.support.WyrmprintAbility
import com.github.lamba92.dragalialost.domain.entities.support.WyrmprintDescription
import com.github.lamba92.dragalialost.domain.utils.appendln
import com.soywiz.klock.DateTime

data class WyrmprintEntity(
    override val name: String,
    val description: WyrmprintDescription,
    override val strength: Int,
    override val hp: Int,
    override val baseMinMight: Int,
    override val baseMaxMight: Int,
    override val obtainedFrom: List<Source>,
    override val releaseDate: DateTime,
    override val availability: List<Availability>,
    val artwork: String,
    val refinedArtwork: String,
    val icon: String,
    val refinedIcon: String,
    override val baseRarity: Rarity,
    override val maxLevel: Int,
    val sellValue: SellValue,
    val featuredCharacter: List<String>,
    val afflictionResistances: Set<Affliction>,
    val elementalResistances: Set<Element>,
    val ability1: WyrmprintAbility,
    val ability2: WyrmprintAbility?,
    val ability3: WyrmprintAbility?,
    val abilityTypes: Set<AbilityType>
) : DragaliaEntity {

    override fun toString() = buildString {
        appendln("Wyrmprint $name:")
        appendln(" - base rarity: ${baseRarity.name}")
        appendln(" - base max might: $baseMaxMight")
        appendln(" - Artworks: ")
        appendln("   • $artwork")
        appendln("   • $refinedArtwork")
        appendln(" - Icons urls: ")
        appendln("   • $icon")
        appendln("   • $refinedIcon")
        appendln(" - Sell value: $sellValue")
        appendln(" - Sell value: ")
        append(" - ability 1:\n$ability1")
        ability2?.let { append(" - ability 2:\n$it") }
        ability3?.let { append(" - ability 3:\n$it") }
        if (elementalResistances.isNotEmpty())
            appendln(" - elemental resistances:")
        elementalResistances.forEach {
            appendln("   • ${it.name}")
        }
        if (afflictionResistances.isNotEmpty())
            appendln(" - affliction resistances:")
        afflictionResistances.forEach {
            appendln("   • ${it.name}")
        }
    }

}