package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.datasource.queries.DragonsCargoQuery
import com.github.lamba92.dragalialost.data.utils.combine
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQueryBuilder

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