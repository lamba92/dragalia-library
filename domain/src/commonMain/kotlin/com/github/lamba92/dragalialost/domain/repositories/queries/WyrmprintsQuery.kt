package com.github.lamba92.dragalialost.domain.repositories.queries

import com.github.lamba92.dragalialost.domain.entities.WyrmprintEntity
import com.github.lamba92.dragalialost.domain.entities.enums.Afflictions
import com.github.lamba92.dragalialost.domain.entities.enums.Element
import com.github.lamba92.dragalialost.domain.entities.enums.Rarity

data class WyrmprintsQuery(
    val name: String?,
    val rarities: Set<Rarity>,
    val afflictionResistances: Set<Afflictions>,
    val elementalResistances: Set<Element>
) {
    operator fun contains(entity: WyrmprintEntity) =
        name?.let { it in entity.name } ?: true &&
                entity.baseRarity in rarities &&
                entity.elementalResistances.all { it in elementalResistances } &&
                entity.afflictionResistances.all { it in afflictionResistances }
}