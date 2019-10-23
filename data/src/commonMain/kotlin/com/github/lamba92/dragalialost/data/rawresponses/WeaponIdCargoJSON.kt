package com.github.lamba92.dragalialost.data.rawresponses

import kotlinx.serialization.Serializable

@Serializable
data class WeaponIdCargoJSON(val cargoquery: List<WeaponIdNestedTitleJson>)

@Serializable
data class WeaponIdNestedTitleJson(val title: WeaponIdJSON)

@Serializable
data class WeaponIdJSON(
    val Id: String,
    val VariationId: String,
    val Rarity: String
)