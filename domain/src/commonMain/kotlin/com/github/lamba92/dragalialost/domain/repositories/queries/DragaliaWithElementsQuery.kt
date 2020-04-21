package com.github.lamba92.dragalialost.domain.repositories.queries

import com.github.lamba92.dragalialost.domain.entities.DragaliaEntity
import com.github.lamba92.dragalialost.domain.entities.DragaliaWithElementEntity
import com.github.lamba92.dragalialost.domain.entities.enums.Element

abstract class DragaliaWithElementsQuery : DragaliaQuery() {

    abstract val elements: Set<Element>

    operator fun <T : DragaliaWithElementEntity> contains(entity: T) =
        contains(entity as DragaliaEntity) &&
                (if (elements.isNotEmpty()) entity.element in elements else true)
}
