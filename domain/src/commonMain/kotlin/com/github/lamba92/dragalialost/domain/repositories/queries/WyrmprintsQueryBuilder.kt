package com.github.lamba92.dragalialost.domain.repositories.queries

import com.github.lamba92.dragalialost.domain.entities.enums.Afflictions
import com.github.lamba92.dragalialost.domain.entities.enums.Element
import com.github.lamba92.dragalialost.domain.entities.enums.Rarity


class WyrmprintsQueryBuilder {

    var name: String? = null
    //    val wyrmprintAbilityTypes = mutableSetOf<WyrmprintAbilityType>()
    val rarities = mutableSetOf<Rarity>()
    val afflictionResistances = mutableSetOf<Afflictions>()
    val elementalResistances = mutableSetOf<Element>()

//    fun addWyrmprintAbilityType(wyrmprintAbilityType: WyrmprintAbilityType) {
//        wyrmprintAbilityTypes.add(wyrmprintAbilityType)
//    }

    fun addRarity(rarity: Rarity) {
        rarities.add(rarity)
    }

    fun addElementalResistance(element: Element) {
        elementalResistances.add(element)
    }

    fun addAfflictionResistance(affliction: Afflictions) {
        afflictionResistances.add(affliction)
    }

    fun anyRarity() =
        Rarity.values().forEach { addRarity(it) }

    fun anyElementalResistance() =
        Element.values().forEach { addElementalResistance(it) }

    fun anyAfflictionResistance() =
        Afflictions.values().forEach { addAfflictionResistance(it) }

    fun toQuery() = WyrmprintsQuery(
        name,
        rarities.toSet(),
        afflictionResistances.toSet(),
        elementalResistances.toSet()
    )
}