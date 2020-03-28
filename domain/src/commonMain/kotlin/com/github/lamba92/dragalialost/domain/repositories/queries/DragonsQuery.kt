package com.github.lamba92.dragalialost.domain.repositories.queries

import com.github.lamba92.dragalialost.domain.entities.DragaliaWithElementEntity
import com.github.lamba92.dragalialost.domain.entities.DragonEntity
import com.github.lamba92.dragalialost.domain.entities.enums.AbilityType
import com.github.lamba92.dragalialost.domain.entities.enums.Element
import com.github.lamba92.dragalialost.domain.entities.enums.Rarity

data class DragonsQuery(
    override val name: String?,
    override val rarities: Set<Rarity>,
    override val abilityTypes: Set<AbilityType>,
    override val elements: Set<Element>,
    val elementalResistances: Set<Element>
) : DragaliaWithElementsQuery() {

    operator fun contains(entity: DragonEntity) =
        contains(entity as DragaliaWithElementEntity) &&
                (if (elementalResistances.isNotEmpty()) entity.elementalResistances.all {
                    it in elementalResistances
                } else true)

}
