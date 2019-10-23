package com.github.lamba92.dragalialost.domain.entities

import com.github.lamba92.dragalialost.domain.entities.enums.*
import com.github.lamba92.dragalialost.domain.entities.support.*
import com.github.lamba92.dragalialost.domain.utils.appendln
import com.soywiz.klock.DateTime

data class AdventurerEntity(
    override val name: String,
    val description: String,
    override val hp: Int,
    override val strength: Int,
    override val maxLevel: Int,
    val bonusHp: ManaCircleBonusStats,
    val bonusStrength: ManaCircleBonusStats,
    override val baseMinMight: Int,
    override val baseMaxMight: Int,
    val defense: Int,
    val heroClass: HeroCLass,
    val gender: Gender,
    val race: Race,
    override val baseRarity: Rarity,
    val voiceActorEN: VoiceActor,
    val voiceActorJP: VoiceActor,
    override val obtainedFrom: List<Source>,
    override val releaseDate: DateTime,
    override val availability: List<Availability>,
    val artworks: List<String>,
    val icons: List<String>,
    val element: Element,
    val weaponType: WeaponType,
    val skill1: AdventurerSkill,
    val skill2: AdventurerSkill,
    val ability: AdventurerAbility,
    val ability2: AdventurerAbility?,
    val ability3: AdventurerAbility?,
    val coAbility: CoAbility
) : DragaliaEntity {

    override fun toString() = buildString {
        appendln("Adventurer $name:")
        appendln(" - ${race.name.toLowerCase()} ${gender.name.toLowerCase()}")
        appendln(" - element: ${element.name}")
        appendln(" - weapon: ${weaponType.name}")
        appendln(" - class: ${heroClass.name}")
        appendln(" - base rarity: ${baseRarity.name}")
        if (artworks.isNotEmpty())
            appendln(" - Artwork urls: ")
        artworks.forEach {
            appendln("   • $it")
        }
        if (icons.isNotEmpty())
            appendln(" - Icon urls: ")
        icons.forEach {
            appendln("   • $it")
        }
    }

}
