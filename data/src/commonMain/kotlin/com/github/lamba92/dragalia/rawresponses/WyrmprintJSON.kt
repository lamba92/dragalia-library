package com.github.lamba92.dragalia.rawresponses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WyrmprintCargoJSON(val cargoquery: List<WyrmprintNestedTitleJson>)

@Serializable
data class WyrmprintNestedTitleJson(val title: WyrmprintJSON)

@Serializable
data class WyrmprintJSON(
    val Abilities11: String,
    val Abilities12: String,
    val Abilities13: String,
    val Abilities21: String,
    val Abilities22: String,
    val Abilities23: String,
    val Abilities31: String,
    val Abilities32: String,
    val Abilities33: String,
    val AmuletType: String,
    val ArtistCV: String,
    val Availability: String,
    val BaseId: String,
    val FeaturedCharacters: String,
    val FlavorText1: String,
    val FlavorText2: String,
    val FlavorText3: String,
    val FlavorText4: String,
    val FlavorText5: String,
    val Id: String,
    val IsPlayable: String,
    val MaxAtk: String,
    val MaxHp: String,
    val MinAtk: String,
    val MinHp: String,
    val Name: String,
    val NameJP: String,
    val Obtain: String,
    val Rarity: String,
    val ReleaseDate: String,
    val ReleaseDate__precision: String? = null,
    @SerialName("ReleaseDate  precision") val ReleaseDate__precision2: String? = null,
    val SellCoin: String,
    val SellDewPoint: String,
    val VariationId: String
) : CargoQueryable
