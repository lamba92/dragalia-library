@file:Suppress("MemberVisibilityCanBePrivate")

package com.github.lamba92.dragalialost.domain.repositories.queries.builders

import com.github.lamba92.dragalialost.domain.entities.enums.Element
import com.github.lamba92.dragalialost.domain.repositories.queries.DragaliaWithElementsQuery


abstract class DragaliaWithElementQueryBuilder<T : DragaliaWithElementsQuery> : DragaliaQueryBuilder<T>() {

    protected val elements = mutableSetOf<Element>()

    fun addElement(element: Element) {
        elements.add(element)
    }

    fun anyElement() =
        Element.values().forEach { addElement(it) }


}

