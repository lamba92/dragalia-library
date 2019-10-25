package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.datasource.queries.WyrmprintsCargoQuery
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQuery

class WyrmprintsQueryMapper(
    private val abilityTypeMapper: AbilityTypeMapper,
    private val elementMapper: ElementMapper,
    private val rarityMapper: RarityMapper
) : SingleToRemoteMapper<List<WyrmprintsCargoQuery>, WyrmprintsQuery> {

    override fun toRemote(entity: WyrmprintsQuery) = with(entity) {
        if (rarities.isNotEmpty())
            rarities.map { WyrmprintsCargoQuery(rarityMapper.toRemote(it), name) }
        else
            listOf(WyrmprintsCargoQuery(null, name))
    }

}
