package com.github.lamba92.dragalialost.domain.repositories.queries

import com.github.lamba92.dragalialost.domain.entities.enums.Rarity


abstract class DragaliaQueryBuilder {
    var name: String? = null
    val rarities = mutableSetOf<Rarity>()

    fun addRarity(rarity: Rarity) {
        rarities.add(rarity)
    }
}