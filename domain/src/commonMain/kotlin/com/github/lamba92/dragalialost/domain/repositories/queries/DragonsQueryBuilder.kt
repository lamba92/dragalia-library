package com.github.lamba92.dragalialost.domain.repositories.queries

import com.github.lamba92.dragalialost.domain.entities.enums.Element
import com.github.lamba92.dragalialost.domain.entities.enums.Rarity

class DragonsQueryBuilder {

    var name: String? = null
    val rarities = mutableSetOf<Rarity>()
    val elements = mutableSetOf<Element>()


    fun addRarity(rarity: Rarity) {
        rarities.add(rarity)
    }

    fun anyRarity() =
        Rarity.values().forEach { addRarity(it) }

    fun addElement(element: Element) {
        elements.add(element)
    }

    fun anyElement() =
        Element.values().forEach { addElement(it) }

}