package com.github.lamba92.dragalialost.data.rawresponses

import kotlinx.serialization.Serializable

@Serializable
data class AdventurerIdCargoJSON(val cargoquery: List<AdventurerIdNestedTitleJson>)

@Serializable
data class AdventurerIdNestedTitleJson(val title: AdventurerIdJSON)

@Serializable
data class AdventurerIdJSON(
    val Id: String,
    val VariationId: String
)