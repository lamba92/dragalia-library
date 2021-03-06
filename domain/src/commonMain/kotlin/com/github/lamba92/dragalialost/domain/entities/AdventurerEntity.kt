package com.github.lamba92.dragalialost.domain.entities

import com.github.lamba92.dragalialost.domain.entities.enums.*
import com.github.lamba92.dragalialost.domain.entities.support.*
import com.github.lamba92.dragalialost.domain.utils.appendln
import kotlinx.serialization.Serializable

@Serializable
data class AdventurerEntity(
    override val id: DragaliaId,
    override val name: String,
    override val maxLevel: Int,
    override val hp: Int,
    override val strength: Int,
    override val baseMinMight: Int,
    override val baseMaxMight: Int,
    override val baseRarity: Rarity,
    override val obtainedFrom: List<Source>,
//    @Serializable(with = DateTimeSerializer::class) override val releaseDate: DateTime,
    override val availability: List<Availability>,
    val icons: Map<Rarity, String?>,
    val artworks: Map<Rarity, String?>,
    val description: String,
    val bonusHp: ManaCircleBonusStats,
    override val abilityTypes: Set<AbilityType>,
    val bonusStrength: ManaCircleBonusStats,
    val defense: Int,
    val heroClass: HeroCLass,
    val gender: Gender,
    val race: Race,
    val voiceActorEN: VoiceActor,
    val voiceActorJP: VoiceActor,
    override val element: Element,
    val weaponType: WeaponType,
    val skill1: AdventurerSkill,
    val skill2: AdventurerSkill,
    val ability1: AdventurerAbility,
    val ability2: AdventurerAbility?,
    val ability3: AdventurerAbility?,
    val coAbility: CoAbility,
    val afflictionResistances: Set<Affliction>
) : DragaliaWithElementEntity {

    override val icon
        get() = icons.minBy { (rarity, _) -> rarity }!!.value
    override val artwork
        get() = artworks.minBy { (rarity, _) -> rarity }!!.value

    override fun toString() = buildString {
        appendln("Adventurer $name:")
        appendln(" - ${race.name.toLowerCase()} ${gender.name.toLowerCase()}")
        appendln(" - element: ${element.name}")
        appendln(" - weapon: ${weaponType.name}")
        appendln(" - class: ${heroClass.name}")
        appendln(" - base rarity: ${baseRarity.name}")
        appendln(" - base max might: $baseMaxMight")
        if (artworks.isNotEmpty())
            appendln(" - artwork urls: ")
        artworks.forEach {
            appendln("   • $it")
        }
        if (icons.isNotEmpty())
            appendln(" - icon urls: ")
        icons.forEach {
            appendln("   • $it")
        }
        append(" - ability 1:\n$ability1")
        ability2?.let { append(" - ability 2:\n$it") }
        ability3?.let { append(" - ability 3:\n$it") }
        append(" - skill 1:\n$skill1")
        append(" - skill 2:\n$skill2")
        append(" - coability:\n$coAbility")
        if (afflictionResistances.isNotEmpty())
            appendln(" - affliction resistances:")
        afflictionResistances.forEach {
            appendln("   • ${it.name}")
        }
    }

}
