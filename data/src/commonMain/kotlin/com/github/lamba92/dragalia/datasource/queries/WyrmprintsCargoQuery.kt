package com.github.lamba92.dragalia.datasource.queries

class WyrmprintsCargoQuery(
    val type: String? = null,
    element: String? = null,
    rarity: Int? = null,
    name: String? = null
) : WithElementAndRarityCargoQuery(element, rarity, name)