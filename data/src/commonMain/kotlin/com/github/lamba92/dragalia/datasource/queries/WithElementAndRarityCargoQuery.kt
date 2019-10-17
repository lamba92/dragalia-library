package com.github.lamba92.dragalia.datasource.queries

open class WithElementAndRarityCargoQuery(
    val element: String? = null,
    val rarity: Int? = null,
    name: String? = null
) : NamedCargoQuery(name)