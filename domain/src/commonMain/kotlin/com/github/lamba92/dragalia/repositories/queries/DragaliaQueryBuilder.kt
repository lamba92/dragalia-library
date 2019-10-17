package com.github.lamba92.dragalia.repositories.queries

import com.github.lamba92.dragalia.entities.enums.Rarity

abstract class DragaliaQueryBuilder {
    var name: String? = null
    val rarities = mutableSetOf<Rarity>()

    fun addRarity(rarity: Rarity) {
        rarities.add(rarity)
    }
}