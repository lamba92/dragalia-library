package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.datasource.queries.AdventurersCargoQuery
import com.github.lamba92.dragalialost.data.utils.combine
import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQuery

class AdventurersQueryMapper(
    private val rarityMapper: RarityMapper,
    private val weaponTypeMapper: WeaponTypeMapper,
    private val heroClassMapper: HeroClassMapper,
    private val elementMapper: ElementMapper
) : SingleToRemoteMapper<List<AdventurersCargoQuery>, AdventurersQuery> {

    override fun toRemote(entity: AdventurersQuery) = with(entity) {
        combine(
            if (weaponTypes.isEmpty()) setOf(null) else weaponTypes,
            if (heroClasses.isEmpty()) setOf(null) else heroClasses,
            if (elements.isEmpty()) setOf(null) else elements,
            if (rarities.isEmpty()) setOf(null) else rarities
        ) { weaponType, heroCLass, element, rarity ->
            AdventurersCargoQuery(
                weaponType?.let { weaponTypeMapper.toRemote(it) },
                heroCLass?.let { heroClassMapper.toRemote(it) },
                element?.let { elementMapper.toRemote(it) },
                rarity?.let { rarityMapper.toRemote(it) },
                name
            )
        }
    }

}


