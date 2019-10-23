package com.github.lamba92.dragalialost.data.rawresponses

import kotlinx.serialization.Serializable

@Serializable
data class WyrmprintIdCargoJSON(val cargoquery: List<WyrmprintIdNestedTitleJson>)

@Serializable
data class WyrmprintIdNestedTitleJson(val title: WyrmprintIdJSON)

@Serializable
data class WyrmprintIdJSON(val Id: String)