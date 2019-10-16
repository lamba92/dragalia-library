package com.github.lamba92.dragalia.entities

import com.github.lamba92.dragalia.entities.enums.*
import com.github.lamba92.dragalia.entities.support.*
import com.soywiz.klock.DateTime

data class AdventurerEntity(
    override val name: String,
    override val description: String,
    override val hp: Int,
    override val strength: Int,
    val bonusHp: ManaCircleBonusStats,
    val bonusStrength: ManaCircleBonusStats,
    override val baseMinMight: Int,
    override val baseMaxMight: Int,
    val defense: Int,
    val heroClass: HeroCLass,
    val gender: Gender,
    val race: Race,
    override val rarity: Rarity,
    val voiceActorEN: VoiceActor,
    val voiceActorJP: VoiceActor,
    override val obtainedFrom: Source,
    override val releaseDate: DateTime,
    override val availability: Availability,
    override val artwork: String,
    override val level: Int,
    val element: Element,
    val weaponType: WeaponType,
    val skill1: Skill,
    val skill2: Skill,
    val ability: CommonAbility,
    val ability2: CommonAbility?,
    val ability3: CommonAbility?,
    val coAbility: CoAbility
) : DragaliaEntity