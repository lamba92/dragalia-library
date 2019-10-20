package com.github.lamba92.dragalialost.data.datasource.queries

open class WithElementAndRarityCargoQuery(
    val element: String? = null,
    val rarity: String? = null,
    name: String? = null
) : NamedCargoQuery(name)