package com.github.lamba92.dragalialost.domain.repositories.queries

import com.github.lamba92.dragalialost.domain.entities.enums.AbilityType
import com.github.lamba92.dragalialost.domain.entities.enums.Affliction
import com.github.lamba92.dragalialost.domain.entities.enums.Element
import com.github.lamba92.dragalialost.domain.entities.enums.Rarity


class WyrmprintsQueryBuilder {

    var name: String? = null
    val rarities = mutableSetOf<Rarity>()
    val afflictionResistances = mutableSetOf<Affliction>()
    val elementalResistances = mutableSetOf<Element>()
    val abilityTypes = mutableSetOf<AbilityType>()

    fun addRarity(rarity: Rarity) {
        rarities.add(rarity)
    }

    fun addElementalResistance(element: Element) {
        elementalResistances.add(element)
    }

    fun addAfflictionResistance(affliction: Affliction) {
        afflictionResistances.add(affliction)
    }

    fun addAbilityType(abilityType: AbilityType) {
        abilityTypes.add(abilityType)
    }

    fun anyRarity() =
        Rarity.values().forEach { addRarity(it) }

    fun toQuery() = WyrmprintsQuery(
        name,
        rarities.toSet(),
        afflictionResistances.toSet(),
        elementalResistances.toSet(),
        abilityTypes.toSet()
    )
}