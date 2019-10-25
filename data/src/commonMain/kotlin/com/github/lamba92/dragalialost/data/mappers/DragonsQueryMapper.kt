package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.datasource.queries.DragonsCargoQuery
import com.github.lamba92.dragalialost.data.utils.combine
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQuery

class DragonsQueryMapper(
    private val rarityMapper: RarityMapper,
    private val elementMapper: ElementMapper
) : SingleToRemoteMapper<List<DragonsCargoQuery>, DragonsQuery> {
    override fun toRemote(entity: DragonsQuery) = with(entity) {
        if (elements.isEmpty() && rarities.isNotEmpty())
            rarities.map {
                DragonsCargoQuery(null, rarityMapper.toRemote(it), name)
            }
        else if (elements.isNotEmpty() && rarities.isEmpty())
            elements.map {
                DragonsCargoQuery(elementMapper.toRemote(it), null, name)
            }
        else if (elements.isNotEmpty() && rarities.isNotEmpty())
            combine(elements, rarities) { element, rarity ->
                DragonsCargoQuery(elementMapper.toRemote(element), rarityMapper.toRemote(rarity), name)
            }
        else
            listOf(DragonsCargoQuery(null, null, name))
    }
}