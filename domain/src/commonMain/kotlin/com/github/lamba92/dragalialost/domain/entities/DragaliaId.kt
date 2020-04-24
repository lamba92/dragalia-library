package com.github.lamba92.dragalialost.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class DragaliaId(val baseId: String, val variationId: String? = null) {
    override fun toString() = buildString {
        append(baseId)
        variationId?.let { append("_$it") }
    }
}
