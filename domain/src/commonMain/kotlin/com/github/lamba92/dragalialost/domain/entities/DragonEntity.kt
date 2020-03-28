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
    override val artwork: String,
    override val icon: String,
    val skill: DragonSkill,
    val ability1: DragonAbility?,
    val ability2: DragonAbility?,
    val basicAttackModifier: List<AttackComboData>,
    override val element: Element,
    val elementalResistances: Set<Element>,
    override val abilityTypes: Set<AbilityType>
) : DragaliaWithElementEntity {

    override fun toString() = buildString {
        appendln("Dragon $name:")
        appendln(" - element: ${element.name}")
        appendln(" - base rarity: ${baseRarity.name}")
        appendln(" - base max might: $baseMaxMight")
        appendln(" - Artwork url: ")
        appendln("   • $artwork")
        appendln(" - Icon urls: ")
        appendln("   • $icon")
        ability1?.let { append(" - ability 1:\n$it") }
        ability2?.let { append(" - ability 2:\n$it") }
        append(" - skill 1:\n$skill")
    }

}
