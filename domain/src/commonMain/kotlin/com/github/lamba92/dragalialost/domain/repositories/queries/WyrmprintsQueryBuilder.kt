package com.github.lamba92.dragalialost.domain.repositories.queries

import com.github.lamba92.dragalialost.domain.entities.enums.WyrmprintAbilityType


class WyrmprintsQueryBuilder : WithElementQueryBuilder() {
    val wyrmprintAbilityTypes = mutableSetOf<WyrmprintAbilityType>()

    fun addWyrmprintAbilityType(wyrmprintAbilityType: WyrmprintAbilityType) {
        wyrmprintAbilityTypes.add(wyrmprintAbilityType)
    }
}