package com.github.lamba92.dragalialost.domain.entities

import com.github.lamba92.dragalialost.domain.entities.enums.*
import com.github.lamba92.dragalialost.domain.entities.support.*
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
    override val rarity: Rarity,
    val sellValue: SellValue,
    val voiceActorEN: VoiceActor,
    val voiceActorJP: VoiceActor,
    val gender: Gender,
    override val obtainedFrom: Source,
    override val releaseDate: DateTime,
    override val availability: List<Availability>,
    override val artwork: String,
    val skill: DragonSkill,
    val ability1: DragonAbility?,
    val ability2: DragonAbility?,
    val basicAttackModifier: List<AttackComboData>,
    val element: Element
) : DragaliaEntity