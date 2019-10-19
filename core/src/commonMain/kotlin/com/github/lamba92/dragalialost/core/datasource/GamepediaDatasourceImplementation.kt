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

    override suspend fun searchAdventurerIds(
        query: AdventurersCargoQuery,
        limit: Int
    ) = with(query) {
        httpClient.get<IdCargoJSON>(
            endpoints.searchAdventurerIdsUrl(name, weaponType, element, heroClass, rarity, limit)
        ).cargoquery.map { it.title.Id }
    }

    override suspend fun searchDragonIds(query: DragonsCargoQuery, limit: Int) = with(query) {
        httpClient.get<IdCargoJSON>(endpoints.searchDragonIdsUrl(name, element, rarity, limit))
            .cargoquery.map { it.title.Id }
    }

    override suspend fun searchWyrmprintIds(query: WyrmprintsCargoQuery, limit: Int) = with(query) {
        httpClient.get<IdCargoJSON>(endpoints.searchWyrmprintIdsUrl(name, element, rarity, limit))
            .cargoquery.map { it.title.Id }
    }

    override suspend fun searchWeaponIds(query: WeaponsCargoQuery, limit: Int) = with(query) {
        httpClient.get<IdCargoJSON>(endpoints.searchWeaponIdsUrl(name, element, rarity, limit))
            .cargoquery.map { it.title.Id }
    }

    override suspend fun searchAbilityIds(query: AbilitiesCargoQuery, limit: Int) = with(query) {
        httpClient.get<IdCargoJSON>(endpoints.searchAbilityIdsUrl(name, limit))
            .cargoquery.map { it.title.Id }
    }

    override suspend fun searchCoAbilityIds(query: CoAbilitiesCargoQuery, limit: Int) = with(query) {
        httpClient.get<IdCargoJSON>(endpoints.searchCoAbilityIdsUrl(name, limit))
            .cargoquery.map { it.title.Id }
    }

    override suspend fun searchSkillIds(query: SkillsCargoQuery, limit: Int) = with(query) {
        httpClient.get<SkillIdCargoJSON>(endpoints.searchSkillIdsUrl(name, limit))
            .cargoquery.map { it.title.SkillId }
    }

    override suspend fun searchAbilityLimitedGroupIds(query: AbilityLimitedGroupsCargoQuery, limit: Int) = with(query) {
        httpClient.get<IdCargoJSON>(
            endpoints.searchAbilityLimitedGroupIdsUrl(name, isEffectMIx, maxLimitedValue, abilityLimitedText, limit)
        ).cargoquery.map { it.title.Id }
    }

    override suspend fun getAdventurersById(id: String) =
        httpClient.get<AdventurerCargoJSON>(endpoints.getAdventurerByIdUrl(id)).cargoquery.first().title

    override suspend fun getDragonsById(id: String) =
        httpClient.get<DragonCargoJSON>(endpoints.getDragonByIdUrl(id)).cargoquery.first().title

    override suspend fun getWyrmprintsById(id: String) =
        httpClient.get<WyrmprintCargoJSON>(endpoints.getWyrmprintByIdUrl(id)).cargoquery.first().title

    override suspend fun getWeaponsById(id: String) =
        httpClient.get<WeaponCargoJSON>(endpoints.getWeaponByIdUrl(id)).cargoquery.first().title

    override suspend fun getAbilitiesById(id: String) =
        httpClient.get<AbilityCargoJSON>(endpoints.getAbilityByIdUrl(id)).cargoquery.first().title

    override suspend fun getCoAbilitiesById(id: String) =
        httpClient.get<CoAbilityCargoJSON>(endpoints.getCoAbilityByIdUrl(id)).cargoquery.first().title

    override suspend fun getSkillsById(id: String) =
        httpClient.get<SkillCargoJSON>(endpoints.getSkillByNameUrl(id)).cargoquery.first().title

    override suspend fun getAbilityLimitedGroupsById(id: String) =
        httpClient.get<AbilityLimitedGroupCargoJSON>(endpoints.getAbilityLimitedGroupByIdUrl(id)).cargoquery.first().title

}