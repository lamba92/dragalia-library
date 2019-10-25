package com.github.lamba92.dragalialost.domain.repositories.queries

import com.github.lamba92.dragalialost.domain.entities.enums.*

class AdventurersQueryBuilder {
    var name: String? = null

    val weaponTypes = mutableSetOf<WeaponType>()
    val rarities = mutableSetOf<Rarity>()
    val heroClasses = mutableSetOf<HeroCLass>()
    val elements = mutableSetOf<Element>()

    val afflictionResistances = mutableSetOf<Affliction>()
    val abilityTypes = mutableSetOf<AbilityType>()


    fun addElement(element: Element) {
        elements.add(element)
    }

    fun addRarity(rarity: Rarity) {
        rarities.add(rarity)
    }

    fun addHeroClass(heroCLass: HeroCLass) {
        heroClasses.add(heroCLass)
    }

    fun addWeaponType(weaponType: WeaponType) {
        weaponTypes.add(weaponType)
    }

    fun addAfflictionResistance(affliction: Affliction) {
        afflictionResistances.add(affliction)
    }

    fun addAbilityType(abilityType: AbilityType) {
        abilityTypes.add(abilityType)
    }

    fun anyElement() =
        Element.values().forEach { addElement(it) }

    fun anyRarity() =
        Rarity.values().forEach { addRarity(it) }

    fun anyHeroClass() =
        HeroCLass.values().forEach { addHeroClass(it) }

    fun anyWeaponType() =
        WeaponType.values().forEach { addWeaponType(it) }

    fun toQuery() = AdventurersQuery(
        name,
        weaponTypes.toSet(),
        heroClasses.toSet(),
        elements.toSet(),
        rarities.toSet(),
        afflictionResistances.toSet(),
        abilityTypes.toSet()
    )

}

