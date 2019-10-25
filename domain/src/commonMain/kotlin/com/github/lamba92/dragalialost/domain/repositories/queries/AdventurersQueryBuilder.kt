package com.github.lamba92.dragalialost.domain.repositories.queries

import com.github.lamba92.dragalialost.domain.entities.enums.*

class AdventurersQueryBuilder {
    var name: String? = null

    val weaponTypes = mutableSetOf<WeaponType>()
    val rarities = mutableSetOf<Rarity>()
    val heroClasses = mutableSetOf<HeroCLass>()
    val elements = mutableSetOf<Element>()

    val afflictionResistances = mutableSetOf<Afflictions>()
    val elementalResistances = mutableSetOf<Element>()


    fun addElement(element: Element) {
        elements.add(element)
    }

    fun anyElement() =
        Element.values().forEach { addElement(it) }

    fun addRarity(rarity: Rarity) {
        rarities.add(rarity)
    }

    fun anyRarity() =
        Rarity.values().forEach { addRarity(it) }

    fun addHeroClass(heroCLass: HeroCLass) {
        heroClasses.add(heroCLass)
    }

    fun addWeaponType(weaponType: WeaponType) {
        weaponTypes.add(weaponType)
    }

    fun addElementalResistance(element: Element) {
        elementalResistances.add(element)
    }

    fun addAfflictionResistance(affliction: Afflictions) {
        afflictionResistances.add(affliction)
    }

    fun anyHeroClass() =
        HeroCLass.values().forEach { addHeroClass(it) }

    fun anyWeaponType() =
        WeaponType.values().forEach { addWeaponType(it) }

    fun anyElementalResistance() =
        Element.values().forEach { addElementalResistance(it) }

    fun anyAfflictionResistance() =
        Afflictions.values().forEach { addAfflictionResistance(it) }

    fun toQuery() = AdventurersQuery(
        name,
        weaponTypes.toSet(),
        heroClasses.toSet(),
        elements.toSet(),
        rarities.toSet(),
        afflictionResistances.toSet(),
        elementalResistances.toSet()
    )

}

