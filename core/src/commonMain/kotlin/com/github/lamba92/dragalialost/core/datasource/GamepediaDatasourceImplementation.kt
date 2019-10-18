package com.github.lamba92.dragalialost.core.datasource

import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasource
import com.github.lamba92.dragalialost.data.datasource.queries.*
import com.github.lamba92.dragalialost.data.rawresponses.*
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class GamepediaDatasourceImplementation(
    private val httpClient: HttpClient,
    private val endpoints: GamepediaDatasource.Endpoints
) : GamepediaDatasource {

    override suspend fun searchAdventurers(
        query: AdventurersCargoQuery,
        limit: Int
    ) = with(query) {
        httpClient.get<AdventurerCargoJSON>(
            endpoints.searchAdventurersUrl(name, weaponType, element, heroClass, rarity, limit)
        )
    }

    override suspend fun searchDragons(query: DragonsCargoQuery, limit: Int) = with(query) {
        httpClient.get<DragonCargoJSON>(endpoints.searchDragonsUrl(name, element, rarity, limit))
    }

    override suspend fun searchWyrmprints(query: WyrmprintsCargoQuery, limit: Int) = with(query) {
        httpClient.get<WyrmprintCargoJSON>(endpoints.searchWyrmprintsUrl(name, element, rarity, limit))
    }

    override suspend fun searchWeapons(query: WeaponsCargoQuery, limit: Int) = with(query) {
        httpClient.get<WeaponCargoJSON>(endpoints.searchWeaponsUrl(name, element, rarity, limit))
    }

    override suspend fun searchAbilities(query: AbilitiesCargoQuery, limit: Int) = with(query) {
        httpClient.get<AbilityCargoJSON>(endpoints.searchAbilitiesUrl(name, limit))
    }

    override suspend fun searchCoAbilities(query: CoAbilitiesCargoQuery, limit: Int) = with(query) {
        httpClient.get<CoAbilityCargoJSON>(endpoints.searchCoAbilitiesUrl(name, limit))
    }

    override suspend fun searchSkills(query: SkillsCargoQuery, limit: Int) = with(query) {
        httpClient.get<SkillCargoJSON>(endpoints.searchSkillsUrl(name, limit))
    }

    override suspend fun searchAbilityLimitedGroups(query: AbilityLimitedGroupsCargoQuery, limit: Int) = with(query) {
        httpClient.get<AbilityLimitedGroupCargoJSON>(
            endpoints.searchAbilityLimitedGroupsUrl(name, isEffectMIx, maxLimitedValue, abilityLimitedText, limit)
        )
    }

}