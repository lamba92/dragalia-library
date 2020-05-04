package com.github.lamba92.dragalialost.data.rawresponses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DragonCargoJSON(val cargoquery: List<DragonNestedTitleJson>)

@Serializable
data class DragonNestedTitleJson(val title: DragonJSON)

@Serializable
data class DragonJSON(
    val Abilities11: String,
    val Abilities12: String,
    val Abilities21: String,
    val Abilities22: String,
    val Availability: String,
    val BaseId: String,
    val DashSpeedRatio: String,
    val ElementalType: String,
    val ElementalTypeId: String,
    val EnglishCV: String,
    val FavoriteType: String,
    val FullName: String,
    val Id: String,
    val IsLongRange: String,
    val IsPlayable: String,
    val IsTurnToDamageDir: String,
    val JapaneseCV: String,
    val MaxAtk: String,
    val MaxHp: String,
    val MinAtk: String,
    val MinHp: String,
    val MoveSpeed: String,
    val MoveType: String,
    val Name: String,
    val NameJP: String,
    val Obtain: String,
    val ProfileText: String,
    val Rarity: String,
    val ReleaseDate: String,
    val ReleaseDate__precision: String? = null,
    @SerialName("ReleaseDate  precision") val ReleaseDate__precision2: String? = null,
    val SellCoin: String,
    val SellDewPoint: String,
    val SkillID: String,
    val SkillDescription: String,
    val SkillName: String,
    val Title: String,
    val TitleJP: String,
    val TurnSpeed: String,
    val VariationId: String
) : CargoQueryable
