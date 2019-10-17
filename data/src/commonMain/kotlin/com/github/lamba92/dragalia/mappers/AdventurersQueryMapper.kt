package com.github.lamba92.dragalia.mappers

import com.github.lamba92.dragalia.datasource.queries.AdventurersCargoQuery
import com.github.lamba92.dragalia.repositories.queries.AdventurersQueryBuilder
import com.github.lamba92.dragalia.utils.combine

class AdventurersQueryMapper(
    private val rarityMapper: RarityMapper,
    private val weaponTypeMapper: WeaponTypeMapper,
    private val heroClassMapper: HeroClassMapper,
    private val elementMapper: ElementMapper
) : SingleToRemoteMapper<List<AdventurersCargoQuery>, AdventurersQueryBuilder> {

    override fun toRemote(entity: AdventurersQueryBuilder) = with(entity) {
        combine(rarities, weaponTypes, heroCLasses, elements) { rarity, weaponType, heroClass, element ->
            AdventurersCargoQuery(
                weaponTypeMapper.toRemote(weaponType),
                heroClassMapper.toRemote(heroClass),
                elementMapper.toRemote(element),
                rarityMapper.toRemote(rarity),
                name
            )
        }
    }

}