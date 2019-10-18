package com.github.lamba92.dragalialost.data.rawresponses

import kotlinx.serialization.Serializable

@Serializable
data class AbilityLimitedGroupCargoJSON(val cargoquery: List<AbilityLimitedGroupNestedTitleJson>)

@Serializable
data class AbilityLimitedGroupNestedTitleJson(val title: AbilityLimitedGroupJSON)

@Serializable
data class AbilityLimitedGroupJSON(
    val Id: String,
    val IsEffectMix: String,
    val MaxLimitedValue: String,
    val AbilityLimitedText: String
) : CargoQueryable