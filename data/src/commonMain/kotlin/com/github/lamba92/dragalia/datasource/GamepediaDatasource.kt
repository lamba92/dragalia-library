package com.github.lamba92.dragalia.datasource

import com.github.lamba92.dragalia.rawresponses.AdventurerJSON
import com.github.lamba92.dragalia.rawresponses.CargoJSON
import com.github.lamba92.dragalia.rawresponses.DragonJSON
import com.github.lamba92.dragalia.rawresponses.WyrmprintJSON
import io.ktor.http.URLProtocol
import io.ktor.http.Url

interface GamepediaDatasource {

    suspend fun searchAdventurers(
        name: String? = null,
        weaponType: String? = null,
        element: String? = null,
        heroClass: String? = null,
        rarity: Int? = null,
        limit: Int
    ): CargoJSON<AdventurerJSON>

    suspend fun searchDragons(
        name: String? = null,
        element: String? = null,
        rarity: Int? = null,
        limit: Int
    ): CargoJSON<DragonJSON>

    suspend fun searchWyrmprints(
        name: String? = null,
        element: String? = null,
        rarity: Int? = null,
        limit: Int
    ): CargoJSON<WyrmprintJSON>

    interface Endpoints {

        val protocol: URLProtocol
        val host: String
        val port: Int

        fun searchAdventurersUrl(
            name: String? = null,
            weaponType: String? = null,
            element: String? = null,
            heroClass: String? = null,
            rarity: Int? = null,
            limit: Int
        ): Url

        fun searchDragonsUrl(
            name: String? = null,
            element: String? = null,
            rarity: Int? = null,
            limit: Int
        ): Url

        fun searchWyrmprintsUrl(
            name: String? = null,
            element: String? = null,
            rarity: Int? = null,
            limit: Int
        ): Url
    }

}