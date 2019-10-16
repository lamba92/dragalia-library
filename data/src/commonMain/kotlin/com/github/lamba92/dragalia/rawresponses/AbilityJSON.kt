package com.github.lamba92.dragalia.rawresponses

import kotlinx.serialization.Serializable

@Serializable
data class AbilityCargoJSON(val cargoquery: List<AbilityNestedTitleJson>)

@Serializable
data class AbilityNestedTitleJson(val title: AbilityJSON)

@Serializable
data class AbilityJSON(
    val AbilityGroup: String,
    val AbilityIconName: String,
    val AbilityLimitedGroupId1: String,
    val AbilityLimitedGroupId2: String,
    val AbilityLimitedGroupId3: String,
    val Details: String,
    val GenericName: String,
    val Id: String,
    val Name: String,
    val PartyPowerWeight: String
) : CargoQueryable