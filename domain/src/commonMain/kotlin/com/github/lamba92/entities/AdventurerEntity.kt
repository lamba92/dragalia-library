package com.github.lamba92.entities

data class AdventurerEntity(
    val name: String,
    val description: String,
    val hp: Int,
    val strength: Int,
    val bonusHp: ManaCircleBonusStats,
    val bonusStrength: ManaCircleBonusStats,
    val baseMinMight: Int,
    val baseMacMight: Int,
    val defense: Int,
    val heroClass: HeroCLass,
    val gender: Gender,
    val race: Race,
    val rarity: Rarity,
    val voiceActorEN: VoiceActor,
    val voiceActorJP: VoiceActor,
    val obtainedFrom: Source,
    )