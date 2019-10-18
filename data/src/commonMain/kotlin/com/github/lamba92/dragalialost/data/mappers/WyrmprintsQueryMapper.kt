package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.datasource.queries.WyrmprintsCargoQuery
import com.github.lamba92.dragalialost.data.utils.combine
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQueryBuilder

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
