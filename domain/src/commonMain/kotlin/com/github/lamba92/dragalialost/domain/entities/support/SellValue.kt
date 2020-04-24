package com.github.lamba92.dragalialost.domain.entities.support

import kotlinx.serialization.Serializable

@Serializable
data class SellValue(
    val rupies: Int,
    val eldwater: Int
) {
    override fun toString() =
        "$rupies rupies, $eldwater eldwater"
}
