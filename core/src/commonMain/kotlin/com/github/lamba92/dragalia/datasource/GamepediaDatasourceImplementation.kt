package com.github.lamba92.dragalia.datasource

import com.github.lamba92.dragalia.rawresponses.AbilityCargoJSON
import com.github.lamba92.dragalia.rawresponses.AdventurerCargoJSON
import com.github.lamba92.dragalia.rawresponses.DragonCargoJSON
import com.github.lamba92.dragalia.rawresponses.WyrmprintCargoJSON
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

    override suspend fun searchDragons(
        name: String?,
        element: String?,
        rarity: Int?,
        limit: Int
    ) = httpClient.get<DragonCargoJSON>(
        endpoints.searchDragonsUrl(
            name,
            element,
            rarity,
            limit
        )
    )

    override suspend fun searchWyrmprints(
        name: String?,
        element: String?,
        rarity: Int?,
        limit: Int
    ) = httpClient.get<WyrmprintCargoJSON>(
        endpoints.searchWyrmprintsUrl(
            name,
            element,
            rarity,
            limit
        )
    )

    override suspend fun searchAbility(name: String?, limit: Int) =
        httpClient.get<AbilityCargoJSON>(endpoints.searchAbilityUrl(name, limit))
}