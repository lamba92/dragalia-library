package com.github.lamba92.dragalialost.domain.repositories.queries

import com.github.lamba92.dragalialost.domain.entities.DragonEntity
import com.github.lamba92.dragalialost.domain.entities.enums.AbilityType
import com.github.lamba92.dragalialost.domain.entities.enums.Element
import com.github.lamba92.dragalialost.domain.entities.enums.Rarity

data class DragonsQuery(
    val name: String?,
    val rarities: Set<Rarity>,
    val elements: Set<Element>,
    val elementalResistances: Set<Element>,
    val abilityTypes: Set<AbilityType>
) {
    operator fun contains(entity: DragonEntity) =
        (if (elements.isNotEmpty()) entity.element in elements else true) &&
                (if (rarities.isNotEmpty()) entity.baseRarity in rarities else true) &&
                (if (abilityTypes.isNotEmpty()) entity.abilityTypes.any {
                    it in abilityTypes
                } else true) &&
                (if (elementalResistances.isNotEmpty()) entity.elementalResistances.all {
                    it in elementalResistances
                } else true) &&
                name?.let { it.toLowerCase() in entity.name.toLowerCase() } ?: true

}