package com.github.lamba92.dragalialost.data.rawresponses

import kotlinx.serialization.Serializable

@Serializable
data class IdCargoJSON(val cargoquery: List<IdNestedTitleJson>)

@Serializable
data class IdNestedTitleJson(val title: IdJSON)

@Serializable
data class IdJSON(
    val Id: String
)