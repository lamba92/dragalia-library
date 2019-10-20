package com.github.lamba92.dragalialost.data.datasource.queries

class AdventurersCargoQuery(
    val weaponType: String? = null,
    val heroClass: String? = null,
    element: String? = null,
    rarity: String? = null,
    name: String? = null
) : WithElementAndRarityCargoQuery(element, rarity, name)