package com.github.lamba92.dragalia.datasource

import com.github.lamba92.dragalia.rawresponses.AdventurerJSON
import com.github.lamba92.dragalia.rawresponses.CargoJSON
import com.github.lamba92.dragalia.rawresponses.DragonJSON
import com.github.lamba92.dragalia.rawresponses.WyrmprintJSON
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
    ) = httpClient.get<CargoJSON<AdventurerJSON>>(
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
    ) = httpClient.get<CargoJSON<DragonJSON>>(
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
    ) = httpClient.get<CargoJSON<WyrmprintJSON>>(
        endpoints.searchDragonsUrl(
            name,
            element,
            rarity,
            limit
        )
    )

}