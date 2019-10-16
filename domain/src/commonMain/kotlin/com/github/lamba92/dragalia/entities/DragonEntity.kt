package com.github.lamba92.dragalia.entities

import com.github.lamba92.dragalia.entities.enums.*
import com.github.lamba92.dragalia.entities.support.*
import com.soywiz.klock.DateTime

data class DragonEntity(
    override val name: String,
    override val description: String,
    override val hp: Int,
    override val strength: Int,
    override val baseMinMight: Int,
    override val baseMaxMight: Int,
    override val rarity: Rarity,
    val sellValue: SellValue,
    val voiceActorEN: VoiceActor,
    val voiceActorJP: VoiceActor,
    val gender: Gender,
    override val obtainedFrom: Source,
    override val releaseDate: DateTime,
    override val availability: Availability,
    override val artwork: String,
    override val level: Int,
    val skill: Skill,
    val isMaxUnbound: Boolean,
    val ability: CommonAbility,
    val basicAttackModifier: AttackModifier,
    val element: Element
) : DragaliaEntity