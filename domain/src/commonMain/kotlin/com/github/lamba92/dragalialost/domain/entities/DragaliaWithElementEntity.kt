package com.github.lamba92.dragalialost.domain.entities

import com.github.lamba92.dragalialost.domain.entities.enums.Element

interface DragaliaWithElementEntity : DragaliaEntity {
    val element: Element
}
