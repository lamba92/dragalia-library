package com.github.lamba92.dragalialost.domain.repositories.queries

import com.github.lamba92.dragalialost.domain.entities.AdventurerEntity
import com.github.lamba92.dragalialost.domain.entities.DragaliaWithElementEntity
import com.github.lamba92.dragalialost.domain.entities.enums.*

data class AdventurersQuery(
    override val name: String?,
    override val rarities: Set<Rarity>,
    override val abilityTypes: Set<AbilityType>,
    override val elements: Set<Element>,
    val weaponTypes: Set<WeaponType>,
    val heroClasses: Set<HeroCLass>,
    val afflictionResistances: Set<Affliction>
) : DragaliaWithElementsQuery() {

    operator fun contains(entity: AdventurerEntity) =
        contains(entity as DragaliaWithElementEntity) &&
                (if (heroClasses.isNotEmpty()) entity.heroClass in heroClasses else true) &&
                (if (weaponTypes.isNotEmpty()) entity.weaponType in weaponTypes else true) &&
                (if (afflictionResistances.isNotEmpty()) entity.afflictionResistances.all {
                    it in afflictionResistances
                } else true)

}
