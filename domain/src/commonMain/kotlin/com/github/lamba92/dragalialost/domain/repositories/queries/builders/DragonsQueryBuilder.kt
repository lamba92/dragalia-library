@file:Suppress("MemberVisibilityCanBePrivate")

package com.github.lamba92.dragalialost.domain.repositories.queries.builders

import com.github.lamba92.dragalialost.domain.entities.enums.Element
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQuery

class DragonsQueryBuilder : DragaliaWithElementQueryBuilder<DragonsQuery>() {

    private val elementalResistances = mutableSetOf<Element>()

    fun addElementalResistance(element: Element) {
        elementalResistances.add(element)
    }

    fun anyElementalResistance() =
        Element.values().forEach { addElementalResistance(it) }

    override fun build() = DragonsQuery(
        name,
        rarities.toSet(),
        abilityTypes.toSet(),
        elements.toSet(),
        elementalResistances.toSet()
    )

}
