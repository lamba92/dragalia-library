package com.github.lamba92.dragalia.repositories.queries

import com.github.lamba92.dragalia.entities.enums.WyrmprintAbilityType

class WyrmprintsQueryBuilder : WithElementQueryBuilder() {
    val wyrmprintAbilityTypes = mutableSetOf<WyrmprintAbilityType>()

    fun addWyrmprintAbilityType(wyrmprintAbilityType: WyrmprintAbilityType) {
        wyrmprintAbilityTypes.add(wyrmprintAbilityType)
    }
}