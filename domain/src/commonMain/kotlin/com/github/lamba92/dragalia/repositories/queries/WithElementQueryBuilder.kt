package com.github.lamba92.dragalia.repositories.queries

import com.github.lamba92.dragalia.entities.enums.Element

open class WithElementQueryBuilder : DragaliaQueryBuilder() {
    val elements = mutableSetOf<Element>()

    fun addElement(element: Element) {
        elements.add(element)
    }
}