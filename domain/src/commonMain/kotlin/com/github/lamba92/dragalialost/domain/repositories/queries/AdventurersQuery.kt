package com.github.lamba92.dragalialost.domain.repositories.queries

import com.github.lamba92.dragalialost.domain.entities.AdventurerEntity
import com.github.lamba92.dragalialost.domain.entities.enums.*

data class AdventurersQuery(
    val name: String?,
    val weaponTypes: Set<WeaponType>,
    val heroClasses: Set<HeroCLass>,
    val elements: Set<Element>,
    val rarities: Set<Rarity>,
    val afflictionResistances: Set<Afflictions>,
    val elementalResistances: Set<Element>
) {

    operator fun contains(entity: AdventurerEntity) = with(entity) {
        heroClass in heroClasses && weaponType in weaponTypes &&
                element in elements && baseRarity in rarities &&
                this@AdventurersQuery.name?.let { it in name } ?: true
    }

}