package com.github.lamba92.dragalialost.domain.repositories.queries

import com.github.lamba92.dragalialost.domain.entities.AdventurerEntity
import com.github.lamba92.dragalialost.domain.entities.enums.*

data class AdventurersQuery(
    val name: String?,
    val weaponTypes: Set<WeaponType>,
    val heroClasses: Set<HeroCLass>,
    val elements: Set<Element>,
    val rarities: Set<Rarity>,
    val afflictionResistances: Set<Affliction>,
    val abilityTypes: Set<AbilityType>
) {

    operator fun contains(entity: AdventurerEntity) =
        (if (heroClasses.isNotEmpty()) entity.heroClass in heroClasses else true) &&
                (if (weaponTypes.isNotEmpty()) entity.weaponType in weaponTypes else true) &&
                (if (elements.isNotEmpty()) entity.element in elements else true) &&
                (if (rarities.isNotEmpty()) entity.baseRarity in rarities else true) &&
                (if (abilityTypes.isNotEmpty()) entity.abilityTypes.all {
                    it in abilityTypes
                } else true) &&
                (if (afflictionResistances.isNotEmpty()) entity.afflictionResistances.all {
                    it in afflictionResistances
                } else true) &&
                name?.let { it.toLowerCase() in entity.name.toLowerCase() } ?: true


}
