package com.github.lamba92.dragalia.mappers

import com.github.lamba92.dragalia.datasource.queries.WyrmprintsCargoQuery
import com.github.lamba92.dragalia.repositories.queries.WyrmprintsQueryBuilder
import com.github.lamba92.dragalia.utils.combine

class WyrmprintsQueryMapper(
    private val wyrmprintAbilityTypeMapper: WyrmprintAbilityTypeMapper,
    private val elementMapper: ElementMapper,
    private val rarityMapper: RarityMapper
) : SingleToRemoteMapper<List<WyrmprintsCargoQuery>, WyrmprintsQueryBuilder> {

    override fun toRemote(entity: WyrmprintsQueryBuilder) = with(entity) {
        combine(wyrmprintAbilityTypes, elements, rarities) { wyrmType, element, rarity ->
            WyrmprintsCargoQuery(
                wyrmprintAbilityTypeMapper.toRemote(wyrmType),
                elementMapper.toRemote(element),
                rarityMapper.toRemote(rarity)
            )
        }
    }

}
