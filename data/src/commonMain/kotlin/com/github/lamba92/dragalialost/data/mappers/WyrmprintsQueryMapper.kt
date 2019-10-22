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
        if (wyrmprintAbilityTypes.isNotEmpty() && elements.isNotEmpty() && rarities.isNotEmpty())
            combine(wyrmprintAbilityTypes, elements, rarities) { wyrmType, element, rarity ->
                WyrmprintsCargoQuery(
                    wyrmprintAbilityTypeMapper.toRemote(wyrmType),
                    elementMapper.toRemote(element),
                    rarityMapper.toRemote(rarity),
                    name
                )
            }
        else if (wyrmprintAbilityTypes.isNotEmpty() && elements.isNotEmpty() && rarities.isEmpty())
            combine(wyrmprintAbilityTypes, elements) { wyrmType, element ->
                WyrmprintsCargoQuery(
                    wyrmprintAbilityTypeMapper.toRemote(wyrmType),
                    elementMapper.toRemote(element),
                    null,
                    name
                )
            }
        else if (wyrmprintAbilityTypes.isNotEmpty() && elements.isEmpty() && rarities.isNotEmpty())
            combine(wyrmprintAbilityTypes, rarities) { wyrmType, rarity ->
                WyrmprintsCargoQuery(
                    wyrmprintAbilityTypeMapper.toRemote(wyrmType),
                    null,
                    rarityMapper.toRemote(rarity),
                    name
                )
            }
        else if (wyrmprintAbilityTypes.isEmpty() && elements.isNotEmpty() && rarities.isNotEmpty())
            combine(elements, rarities) { element, rarity ->
                WyrmprintsCargoQuery(
                    null,
                    elementMapper.toRemote(element),
                    rarityMapper.toRemote(rarity),
                    name
                )
            }
        else if (wyrmprintAbilityTypes.isNotEmpty() && elements.isEmpty() && rarities.isEmpty())
            wyrmprintAbilityTypes.map {
                WyrmprintsCargoQuery(
                    wyrmprintAbilityTypeMapper.toRemote(it),
                    null,
                    null,
                    name
                )
            }
        else if (wyrmprintAbilityTypes.isEmpty() && elements.isNotEmpty() && rarities.isEmpty())
            elements.map {
                WyrmprintsCargoQuery(
                    null,
                    elementMapper.toRemote(it),
                    null,
                    name
                )
            }
        else if (wyrmprintAbilityTypes.isEmpty() && elements.isEmpty() && rarities.isNotEmpty())
            rarities.map {
                WyrmprintsCargoQuery(
                    null,
                    null,
                    rarityMapper.toRemote(it),
                    name
                )
            }
        else
            listOf(WyrmprintsCargoQuery(null, null, null, name))
    }

}
