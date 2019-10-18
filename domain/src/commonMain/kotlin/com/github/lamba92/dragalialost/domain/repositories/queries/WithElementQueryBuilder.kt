package com.github.lamba92.dragalialost.domain.repositories.queries

import com.github.lamba92.dragalialost.domain.entities.enums.Element


open class WithElementQueryBuilder : DragaliaQueryBuilder() {
    val elements = mutableSetOf<Element>()

    fun addElement(element: Element) {
        elements.add(element)
    }
}