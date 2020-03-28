@file:Suppress("MemberVisibilityCanBePrivate")

package com.github.lamba92.dragalialost.domain.repositories.queries.builders

import com.github.lamba92.dragalialost.domain.entities.enums.Affliction
import com.github.lamba92.dragalialost.domain.entities.enums.Element
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQuery

class WyrmprintsQueryBuilder : DragaliaQueryBuilder<WyrmprintsQuery>() {

    val afflictionResistances = mutableSetOf<Affliction>()
    val elementalResistances = mutableSetOf<Element>()

    fun addElementalResistance(element: Element) {
        elementalResistances.add(element)
    }

    fun addAfflictionResistance(affliction: Affliction) {
        afflictionResistances.add(affliction)
    }

    fun anyElementalResistance() =
        Element.values().forEach { addElementalResistance(it) }

    fun anyAfflictionResistance() =
        Affliction.values().forEach { addAfflictionResistance(it) }

    override fun build() = WyrmprintsQuery(
        name,
        rarities.toSet(),
        abilityTypes.toSet(),
        afflictionResistances.toSet(),
        elementalResistances.toSet()
    )
}
