package com.github.lamba92.datasource

import com.github.lamba92.rawresponses.AdventurerJSON
import com.github.lamba92.rawresponses.CargoJSON
import com.github.lamba92.rawresponses.DragonJSON
import com.github.lamba92.rawresponses.WyrmprintJSON
import io.ktor.http.Url

interface GamepediaDatasource {

    fun searchAdventurers(
        name: String?,
        weaponType: String?,
        element: String?,
        heroClass: String?,
        rarity: Int,
        limit: Int
    ): CargoJSON<AdventurerJSON>

    fun searchDragons(
        name: String?,
        weaponType: String?,
        element: String?,
        heroClass: String?,
        rarity: Int,
        limit: Int
    ): CargoJSON<DragonJSON>

    fun searchWyrmprints(
        name: String?,
        weaponType: String?,
        element: String?,
        heroClass: String?,
        rarity: Int,
        limit: Int
    ): CargoJSON<WyrmprintJSON>

    interface Endpoints {
        fun searchAdventurersByNameUrl(query: String, limit: Int): Url
    }

}