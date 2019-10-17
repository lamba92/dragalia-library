package com.github.lamba92.dragalia.repositories.queries

import com.github.lamba92.dragalia.entities.enums.HeroCLass
import com.github.lamba92.dragalia.entities.enums.WeaponType

class AdventurersQueryBuilder : WithElementQueryBuilder() {
    val weaponTypes = mutableSetOf<WeaponType>()
    val heroCLasses = mutableSetOf<HeroCLass>()

    fun addHeroClass(heroCLass: HeroCLass) {
        heroCLasses.add(heroCLass)
    }

    fun addWeaponType(weaponType: WeaponType) {
        weaponTypes.add(weaponType)
    }

}