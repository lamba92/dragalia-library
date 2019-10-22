package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.datasource.queries.AdventurersCargoQuery
import com.github.lamba92.dragalialost.data.utils.combine
import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQueryBuilder

class AdventurersQueryMapper(
    private val rarityMapper: RarityMapper,
    private val weaponTypeMapper: WeaponTypeMapper,
    private val heroClassMapper: HeroClassMapper,
    private val elementMapper: ElementMapper
) : SingleToRemoteMapper<List<AdventurersCargoQuery>, AdventurersQueryBuilder> {

    override fun toRemote(entity: AdventurersQueryBuilder) = with(entity) {
        if (rarities.isEmpty() && weaponTypes.isNotEmpty() && heroCLasses.isNotEmpty() && elements.isNotEmpty())
            combine(weaponTypes, heroCLasses, elements) { weaponType, heroClass, element ->
                AdventurersCargoQuery(
                    weaponTypeMapper.toRemote(weaponType),
                    heroClassMapper.toRemote(heroClass),
                    elementMapper.toRemote(element),
                    null,
                    name
                )
            }
        else if (rarities.isNotEmpty() && weaponTypes.isEmpty() && heroCLasses.isNotEmpty() && elements.isNotEmpty())
            combine(rarities, heroCLasses, elements) { rarity, heroClass, element ->
                AdventurersCargoQuery(
                    null,
                    heroClassMapper.toRemote(heroClass),
                    elementMapper.toRemote(element),
                    rarityMapper.toRemote(rarity),
                    name
                )
            }
        else if (rarities.isNotEmpty() && weaponTypes.isNotEmpty() && heroCLasses.isEmpty() && elements.isNotEmpty())
            combine(rarities, weaponTypes, elements) { rarity, weaponType, element ->
                AdventurersCargoQuery(
                    weaponTypeMapper.toRemote(weaponType),
                    null,
                    elementMapper.toRemote(element),
                    rarityMapper.toRemote(rarity),
                    name
                )
            }
        else if (rarities.isNotEmpty() && weaponTypes.isNotEmpty() && heroCLasses.isNotEmpty() && elements.isEmpty())
            combine(rarities, weaponTypes, heroCLasses) { rarity, weaponType, heroClass ->
                AdventurersCargoQuery(
                    weaponTypeMapper.toRemote(weaponType),
                    heroClassMapper.toRemote(heroClass),
                    null,
                    rarityMapper.toRemote(rarity),
                    name
                )
            }
        else if (rarities.isEmpty() && weaponTypes.isEmpty() && heroCLasses.isNotEmpty() && elements.isNotEmpty())
            combine(heroCLasses, elements) { heroClass, element ->
                AdventurersCargoQuery(
                    null,
                    heroClassMapper.toRemote(heroClass),
                    elementMapper.toRemote(element),
                    null,
                    name
                )
            }
        else if (rarities.isEmpty() && weaponTypes.isNotEmpty() && heroCLasses.isEmpty() && elements.isNotEmpty())
            combine(weaponTypes, elements) { weaponType, element ->
                AdventurersCargoQuery(
                    weaponTypeMapper.toRemote(weaponType),
                    null,
                    elementMapper.toRemote(element),
                    null,
                    name
                )
            }
        else if (rarities.isEmpty() && weaponTypes.isNotEmpty() && heroCLasses.isNotEmpty() && elements.isEmpty())
            combine(weaponTypes, heroCLasses) { weaponType, heroClass ->
                AdventurersCargoQuery(
                    weaponTypeMapper.toRemote(weaponType),
                    heroClassMapper.toRemote(heroClass),
                    null,
                    null,
                    name
                )
            }
        else if (rarities.isEmpty() && weaponTypes.isEmpty() && heroCLasses.isEmpty() && elements.isNotEmpty())
            elements.map {
                AdventurersCargoQuery(
                    null,
                    null,
                    elementMapper.toRemote(it),
                    null,
                    name
                )
            }
        else if (rarities.isEmpty() && weaponTypes.isEmpty() && heroCLasses.isNotEmpty() && elements.isEmpty())
            heroCLasses.map {
                AdventurersCargoQuery(
                    null,
                    heroClassMapper.toRemote(it),
                    null,
                    null,
                    name
                )
            }
        else if (rarities.isEmpty() && weaponTypes.isNotEmpty() && heroCLasses.isEmpty() && elements.isEmpty())
            weaponTypes.map {
                AdventurersCargoQuery(
                    weaponTypeMapper.toRemote(it),
                    null,
                    null,
                    null,
                    name
                )
            }
        else if (rarities.isNotEmpty() && weaponTypes.isEmpty() && heroCLasses.isEmpty() && elements.isEmpty())
            rarities.map {
                AdventurersCargoQuery(
                    null,
                    null,
                    null,
                    rarityMapper.toRemote(it),
                    name
                )
            }
        else
            listOf(AdventurersCargoQuery(null, null, null, null, name))

    }

}