package com.github.lamba92.dragalia.mappers

import com.github.lamba92.dragalia.datasource.queries.DragonsCargoQuery
import com.github.lamba92.dragalia.repositories.queries.DragonsQueryBuilder
import com.github.lamba92.dragalia.utils.combine

class DragonsQueryMapper(
    private val rarityMapper: RarityMapper,
    private val elementMapper: ElementMapper
) : SingleToRemoteMapper<List<DragonsCargoQuery>, DragonsQueryBuilder> {
    override fun toRemote(entity: DragonsQueryBuilder) = with(entity) {
        combine(elements, rarities) { element, rarity ->
            DragonsCargoQuery(elementMapper.toRemote(element), rarityMapper.toRemote(rarity), name)
        }
    }
}