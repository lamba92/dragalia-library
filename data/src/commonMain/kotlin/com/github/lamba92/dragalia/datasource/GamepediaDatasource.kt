package com.github.lamba92.dragalia.datasource

import com.github.lamba92.dragalia.rawresponses.AbilityCargoJSON
import com.github.lamba92.dragalia.rawresponses.AdventurerCargoJSON
import com.github.lamba92.dragalia.rawresponses.DragonCargoJSON
import com.github.lamba92.dragalia.rawresponses.WyrmprintCargoJSON
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
    ): AdventurerCargoJSON

    suspend fun searchDragons(
        name: String? = null,
        element: String? = null,
        rarity: Int? = null,
        limit: Int
    ): DragonCargoJSON

    suspend fun searchWyrmprints(
        name: String? = null,
        element: String? = null,
        rarity: Int? = null,
        limit: Int
    ): WyrmprintCargoJSON

    suspend fun searchAbility(
        name: String? = null,
        limit: Int
    ): AbilityCargoJSON

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

        fun searchAbilityUrl(
            name: String? = null,
            limit: Int
        ): Url
    }

}