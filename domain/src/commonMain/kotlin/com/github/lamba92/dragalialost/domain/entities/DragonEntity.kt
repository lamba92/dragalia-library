package com.github.lamba92.dragalialost.domain.entities

import com.github.lamba92.dragalialost.domain.entities.enums.*
import com.github.lamba92.dragalialost.domain.entities.support.*
import com.github.lamba92.dragalialost.domain.utils.appendln
import com.soywiz.klock.DateTime

data class DragonEntity(
    override val name: String,
    val description: String,
    override val hp: Int,
    override val strength: Int,
    override val maxLevel: Int,
    override val baseMinMight: Int,
//    val baseMinMightBonded: Int,
    override val baseMaxMight: Int,
//    val baseMaxMightBonded: Int,
    override val baseRarity: Rarity,
    val sellValue: SellValue,
    val voiceActorEN: VoiceActor,
    val voiceActorJP: VoiceActor,
    val gender: Gender,
    override val obtainedFrom: List<Source>,
    override val releaseDate: DateTime,
    override val availability: List<Availability>,
    val artwork: String,
    val icon: String,
    val skill: DragonSkill,
    val ability1: DragonAbility?,
    val ability2: DragonAbility?,
    val basicAttackModifier: List<AttackComboData>,
    val element: Element
) : DragaliaEntity {

    override fun toString() = buildString {
        appendln("Adventurer $name:")
        appendln(" - element: ${element.name}")
        appendln(" - base rarity: ${baseRarity.name}")
        appendln(" - Artwork url: ")
        appendln("   • $artwork")
        appendln(" - Icon urls: ")
        appendln("   • $icon")
    }

}