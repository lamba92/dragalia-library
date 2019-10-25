package com.github.lamba92.dragalialost.domain.repositories.queries

import com.github.lamba92.dragalialost.domain.entities.DragonEntity
import com.github.lamba92.dragalialost.domain.entities.enums.Element
import com.github.lamba92.dragalialost.domain.entities.enums.Rarity

data class DragonsQuery(
    val name: String?,
    val rarities: Set<Rarity>,
    val elements: Set<Element>
) {
    operator fun contains(entity: DragonEntity) =
        name?.let { it in entity.name } ?: true &&
                entity.baseRarity in rarities &&
                entity.element in elements
}