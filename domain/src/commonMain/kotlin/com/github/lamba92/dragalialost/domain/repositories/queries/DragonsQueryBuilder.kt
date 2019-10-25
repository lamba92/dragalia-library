package com.github.lamba92.dragalialost.domain.repositories.queries

import com.github.lamba92.dragalialost.domain.entities.enums.AbilityType
import com.github.lamba92.dragalialost.domain.entities.enums.Element
import com.github.lamba92.dragalialost.domain.entities.enums.Rarity

class DragonsQueryBuilder {

    var name: String? = null
    val rarities = mutableSetOf<Rarity>()
    val elements = mutableSetOf<Element>()
    val elementalResistances = mutableSetOf<Element>()
    val abilityTypes = mutableSetOf<AbilityType>()


    fun addRarity(rarity: Rarity) {
        rarities.add(rarity)
    }

    fun addElement(element: Element) {
        elements.add(element)
    }

    fun addElementalResistance(element: Element) {
        elementalResistances.add(element)
    }

    fun addAbilityType(abilityType: AbilityType) {
        abilityTypes.add(abilityType)
    }

    fun anyRarity() =
        Rarity.values().forEach { addRarity(it) }

    fun anyElement() =
        Element.values().forEach { addElement(it) }

    fun toQuery() = DragonsQuery(
        name,
        rarities.toSet(),
        elements.toSet(),
        elementalResistances.toSet(),
        abilityTypes.toSet()
    )

}