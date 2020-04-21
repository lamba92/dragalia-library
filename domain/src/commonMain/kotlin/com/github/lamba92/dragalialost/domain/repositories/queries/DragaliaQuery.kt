package com.github.lamba92.dragalialost.domain.repositories.queries

import com.github.lamba92.dragalialost.domain.entities.DragaliaEntity
import com.github.lamba92.dragalialost.domain.entities.enums.AbilityType
import com.github.lamba92.dragalialost.domain.entities.enums.Rarity

abstract class DragaliaQuery {

    abstract val name: String?
    abstract val rarities: Set<Rarity>
    abstract val abilityTypes: Set<AbilityType>

    operator fun <T : DragaliaEntity> contains(entity: T) =
        (if (abilityTypes.isNotEmpty()) entity.abilityTypes.all { it in abilityTypes } else true) &&
                (if (rarities.isNotEmpty()) entity.baseRarity in rarities else true) &&
                name?.let { it.toLowerCase() in entity.name.toLowerCase() } ?: true

}
