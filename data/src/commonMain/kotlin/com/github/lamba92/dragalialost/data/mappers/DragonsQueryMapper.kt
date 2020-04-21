package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.datasource.queries.DragonsCargoQuery
import com.github.lamba92.dragalialost.data.utils.combine
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQuery

class DragonsQueryMapper(
    private val rarityMapper: RarityMapper,
    private val elementMapper: ElementMapper
) : SingleToRemoteMapper<List<DragonsCargoQuery>, DragonsQuery> {

    override fun toRemote(entity: DragonsQuery) = with(entity) {
        combine(
            if (elements.isEmpty()) setOf(null) else elements,
            if (rarities.isEmpty()) setOf(null) else rarities
        ) { element, rarity ->
            DragonsCargoQuery(
                element?.let { elementMapper.toRemote(it) },
                rarity?.let { rarityMapper.toRemote(it) },
                name
            )
        }
    }

}
