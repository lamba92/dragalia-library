package com.github.lamba92.dragalialost.data.rawresponses

import kotlinx.serialization.Serializable

@Serializable
data class CoAbilityCargoJSON(val cargoquery: List<CoAbilityNestedTitleJson>)

@Serializable
data class CoAbilityNestedTitleJson(val title: CoAbilityJSON)

@Serializable
data class CoAbilityJSON(
    val AbilityIconName: String,
    val Category: String,
    val Details: String,
    val GenericName: String,
    val Id: String,
    val Name: String,
    val PartyPowerWeight: String
) : CargoQueryable