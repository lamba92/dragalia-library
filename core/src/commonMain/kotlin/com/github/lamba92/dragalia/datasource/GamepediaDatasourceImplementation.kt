package com.github.lamba92.dragalia.datasource

import com.github.lamba92.dragalia.rawresponses.*
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class GamepediaDatasourceImplementation(
    private val httpClient: HttpClient,
    private val endpoints: GamepediaDatasource.Endpoints
) : GamepediaDatasource {

    override suspend fun searchAdventurers(
        name: String?,
        weaponType: String?,
        element: String?,
        heroClass: String?,
        rarity: Int?,
        limit: Int
    ) = httpClient.get<AdventurerCargoJSON>(
        endpoints.searchAdventurersUrl(
            name,
            weaponType,
            element,
            heroClass,
            rarity,
            limit
        )
    )

    override suspend fun searchDragons(name: String?, element: String?, rarity: Int?, limit: Int) =
        httpClient.get<DragonCargoJSON>(endpoints.searchDragonsUrl(name, element, rarity, limit))

    override suspend fun searchWyrmprints(name: String?, element: String?, type: String?, rarity: Int?, limit: Int) =
        httpClient.get<WyrmprintCargoJSON>(endpoints.searchWyrmprintsUrl(name, element, rarity, limit))

    override suspend fun searchWeapons(name: String?, element: String?, rarity: Int?, limit: Int) =
        httpClient.get<WeaponCargoJSON>(endpoints.searchWeaponsUrl(name, element, rarity, limit))

    override suspend fun searchAbilities(name: String?, limit: Int) =
        httpClient.get<AbilityCargoJSON>(endpoints.searchAbilitiesUrl(name, limit))

    override suspend fun searchCoAbilities(name: String?, limit: Int) =
        httpClient.get<CoAbilityCargoJSON>(endpoints.searchCoAbilitiesUrl(name, limit))

    override suspend fun searchSkills(name: String?, limit: Int) =
        httpClient.get<SkillCargoJSON>(endpoints.searchSkillsUrl(name, limit))

    override suspend fun searchAbilityLimitedGroups(
        name: String?,
        isEffectMIx: Boolean?,
        maxLimitedValue: Int?,
        abilityLimitedText: String?,
        limit: Int
    ) = httpClient.get<AbilityLimitedGroupCargoJSON>(
        endpoints.searchAbilityLimitedGroupsUrl(
            name,
            isEffectMIx,
            maxLimitedValue,
            abilityLimitedText,
            limit
        )
    )
}