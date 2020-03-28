@file:Suppress("MemberVisibilityCanBePrivate")

package com.github.lamba92.dragalialost.domain.repositories.queries.builders

import com.github.lamba92.dragalialost.domain.entities.enums.Affliction
import com.github.lamba92.dragalialost.domain.entities.enums.HeroCLass
import com.github.lamba92.dragalialost.domain.entities.enums.WeaponType
import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQuery

class AdventurersQueryBuilder : DragaliaWithElementQueryBuilder<AdventurersQuery>() {

    private val weaponTypes = mutableSetOf<WeaponType>()
    private val heroClasses = mutableSetOf<HeroCLass>()
    private val afflictionResistances = mutableSetOf<Affliction>()

    fun addHeroClass(heroCLass: HeroCLass) {
        heroClasses.add(heroCLass)
    }

    fun addWeaponType(weaponType: WeaponType) {
        weaponTypes.add(weaponType)
    }

    fun addAfflictionResistance(affliction: Affliction) {
        afflictionResistances.add(affliction)
    }

    fun anyHeroClass() =
        HeroCLass.values().forEach { addHeroClass(it) }

    fun anyWeaponType() =
        WeaponType.values().forEach { addWeaponType(it) }

    fun anyAfflictionResistance() =
        Affliction.values().forEach { addAfflictionResistance(it) }

    override fun build() = AdventurersQuery(
        name,
        rarities.toSet(),
        abilityTypes.toSet(),
        elements.toSet(),
        weaponTypes.toSet(),
        heroClasses.toSet(),
        afflictionResistances.toSet()
    )
}

