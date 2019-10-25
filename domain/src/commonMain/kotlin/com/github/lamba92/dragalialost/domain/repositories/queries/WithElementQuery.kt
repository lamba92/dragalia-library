package com.github.lamba92.dragalialost.domain.repositories.queries

import com.github.lamba92.dragalialost.domain.entities.DragonEntity
import com.github.lamba92.dragalialost.domain.entities.enums.Element
import com.github.lamba92.dragalialost.domain.entities.enums.Rarity

data class WithElementQuery(
    val name: String?,
    val elements: Set<Element>,
    val rarities: Set<Rarity>
) {

    operator fun contains(entity: DragonEntity) = with(entity) {
        this@WithElementQuery.name?.let { it in name } ?: true &&
                element in elements && baseRarity in rarities
    }

}