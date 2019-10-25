package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.datasource.queries.AdventurersCargoQuery
import com.github.lamba92.dragalialost.data.utils.combine
import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQuery

class AdventurersQueryMapper(
    private val rarityMapper: RarityMapper,
    private val weaponTypeMapper: WeaponTypeMapper,
    private val heroClassMapper: HeroClassMapper,
    private val elementMapper: ElementMapper
) : SingleToRemoteMapper<List<AdventurersCargoQuery>, AdventurersQuery> {

    override fun toRemote(entity: AdventurersQuery) = with(entity) {
        if (rarities.isEmpty() && weaponTypes.isNotEmpty() && heroClasses.isNotEmpty() && elements.isNotEmpty())
            combine(weaponTypes, heroClasses, elements) { weaponType, heroClass, element ->
                AdventurersCargoQuery(
                    weaponTypeMapper.toRemote(weaponType),
                    heroClassMapper.toRemote(heroClass),
                    elementMapper.toRemote(element),
                    null,
                    name
                )
            }
        else if (rarities.isNotEmpty() && weaponTypes.isEmpty() && heroClasses.isNotEmpty() && elements.isNotEmpty())
            combine(rarities, heroClasses, elements) { rarity, heroClass, element ->
                AdventurersCargoQuery(
                    null,
                    heroClassMapper.toRemote(heroClass),
                    elementMapper.toRemote(element),
                    rarityMapper.toRemote(rarity),
                    name
                )
            }
        else if (rarities.isNotEmpty() && weaponTypes.isNotEmpty() && heroClasses.isEmpty() && elements.isNotEmpty())
            combine(rarities, weaponTypes, elements) { rarity, weaponType, element ->
                AdventurersCargoQuery(
                    weaponTypeMapper.toRemote(weaponType),
                    null,
                    elementMapper.toRemote(element),
                    rarityMapper.toRemote(rarity),
                    name
                )
            }
        else if (rarities.isNotEmpty() && weaponTypes.isNotEmpty() && heroClasses.isNotEmpty() && elements.isEmpty())
            combine(rarities, weaponTypes, heroClasses) { rarity, weaponType, heroClass ->
                AdventurersCargoQuery(
                    weaponTypeMapper.toRemote(weaponType),
                    heroClassMapper.toRemote(heroClass),
                    null,
                    rarityMapper.toRemote(rarity),
                    name
                )
            }
        else if (rarities.isEmpty() && weaponTypes.isEmpty() && heroClasses.isNotEmpty() && elements.isNotEmpty())
            combine(heroClasses, elements) { heroClass, element ->
                AdventurersCargoQuery(
                    null,
                    heroClassMapper.toRemote(heroClass),
                    elementMapper.toRemote(element),
                    null,
                    name
                )
            }
        else if (rarities.isEmpty() && weaponTypes.isNotEmpty() && heroClasses.isEmpty() && elements.isNotEmpty())
            combine(weaponTypes, elements) { weaponType, element ->
                AdventurersCargoQuery(
                    weaponTypeMapper.toRemote(weaponType),
                    null,
                    elementMapper.toRemote(element),
                    null,
                    name
                )
            }
        else if (rarities.isEmpty() && weaponTypes.isNotEmpty() && heroClasses.isNotEmpty() && elements.isEmpty())
            combine(weaponTypes, heroClasses) { weaponType, heroClass ->
                AdventurersCargoQuery(
                    weaponTypeMapper.toRemote(weaponType),
                    heroClassMapper.toRemote(heroClass),
                    null,
                    null,
                    name
                )
            }
        else if (rarities.isEmpty() && weaponTypes.isEmpty() && heroClasses.isEmpty() && elements.isNotEmpty())
            elements.map {
                AdventurersCargoQuery(
                    null,
                    null,
                    elementMapper.toRemote(it),
                    null,
                    name
                )
            }
        else if (rarities.isEmpty() && weaponTypes.isEmpty() && heroClasses.isNotEmpty() && elements.isEmpty())
            heroClasses.map {
                AdventurersCargoQuery(
                    null,
                    heroClassMapper.toRemote(it),
                    null,
                    null,
                    name
                )
            }
        else if (rarities.isEmpty() && weaponTypes.isNotEmpty() && heroClasses.isEmpty() && elements.isEmpty())
            weaponTypes.map {
                AdventurersCargoQuery(
                    weaponTypeMapper.toRemote(it),
                    null,
                    null,
                    null,
                    name
                )
            }
        else if (rarities.isNotEmpty() && weaponTypes.isEmpty() && heroClasses.isEmpty() && elements.isEmpty())
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