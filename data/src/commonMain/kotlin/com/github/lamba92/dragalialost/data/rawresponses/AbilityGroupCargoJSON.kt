package com.github.lamba92.dragalialost.data.rawresponses

import kotlinx.serialization.Serializable

@Serializable
data class AbilityGroupCargoJSON(val cargoquery: List<AbilityGroupNestedTitleJson>)

@Serializable
data class AbilityGroupNestedTitleJson(val title: AbilityGroupJSON)

@Serializable
data class AbilityGroupJSON(
    val Id: String,
    val GroupName: String
) : CargoQueryable