package com.github.lamba92.dragalialost.domain.entities.enums

import kotlinx.serialization.Serializable

@Serializable
class Availability(val name: String) {
    override fun toString() = name
}
