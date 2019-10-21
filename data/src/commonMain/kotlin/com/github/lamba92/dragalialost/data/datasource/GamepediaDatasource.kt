package com.github.lamba92.dragalialost.data.datasource

import com.github.lamba92.dragalialost.data.datasource.queries.*
import com.github.lamba92.dragalialost.data.rawresponses.*
import io.ktor.http.URLProtocol
import io.ktor.http.Url

interface GamepediaDatasource {

    suspend fun searchAdventurerIds(query: AdventurersCargoQuery, limit: Int = 500): List<String>
    suspend fun searchDragonIds(query: DragonsCargoQuery, limit: Int = 500): List<String>
    suspend fun searchWyrmprintIds(query: WyrmprintsCargoQuery, limit: Int = 500): List<String>
    suspend fun searchWeaponIds(query: WeaponsCargoQuery, limit: Int = 500): List<String>
    suspend fun searchAbilityIds(query: AbilitiesCargoQuery, limit: Int = 500): List<String>
    suspend fun searchCoAbilityIds(query: CoAbilitiesCargoQuery, limit: Int = 500): List<String>
    suspend fun searchSkillIds(query: SkillsCargoQuery, limit: Int = 500): List<String>
    suspend fun searchAbilityLimitedGroupIds(query: AbilityLimitedGroupsCargoQuery, limit: Int = 500): List<String>

    suspend fun getAdventurerById(id: String): AdventurerJSON
    suspend fun getDragonById(id: String): DragonJSON
    suspend fun getWyrmprintById(id: String): WyrmprintJSON
    suspend fun getWeaponsById(id: String): WeaponJSON
    suspend fun getAbilityById(id: String): AbilityJSON
    suspend fun getCoAbilityById(id: String): CoAbilityJSON
    suspend fun getSkillByName(id: String): SkillJSON
    suspend fun getAbilityLimitedGroupsById(id: String): AbilityLimitedGroupJSON

    interface Endpoints {

        val protocol: URLProtocol
        val host: String
        val port: Int
        val path: String

        fun searchAdventurerIdsUrl(
            name: String? = null,
            weaponType: String? = null,
            element: String? = null,
            heroClass: String? = null,
            rarity: String? = null,
            limit: Int = 500
        ): Url

        fun searchDragonIdsUrl(
            name: String? = null,
            element: String? = null,
            rarity: String? = null,
            limit: Int = 500
        ): Url

        fun searchWyrmprintIdsUrl(
            name: String? = null,
            element: String? = null,
            rarity: String? = null,
            limit: Int = 500
        ): Url

        fun searchWeaponIdsUrl(
            name: String? = null,
            element: String? = null,
            rarity: String? = null,
            limit: Int = 500
        ): Url

        fun searchAbilityIdsUrl(
            name: String? = null,
            limit: Int = 500
        ): Url

        fun searchCoAbilityIdsUrl(
            name: String? = null,
            limit: Int = 500
        ): Url

        fun searchSkillIdsUrl(
            name: String? = null,
            limit: Int = 500
        ): Url

        fun searchAbilityLimitedGroupIdsUrl(
            name: String? = null,
            isEffectMIx: Boolean? = null,
            maxLimitedValue: Int? = null,
            abilityLimitedText: String? = null,
            limit: Int = 500
        ): Url

        fun getAdventurerByIdUrl(id: String): Url

        fun getDragonByIdUrl(id: String): Url

        fun getWyrmprintByIdUrl(id: String): Url

        fun getWeaponByIdUrl(id: String): Url

        fun getAbilityByIdUrl(id: String): Url

        fun getCoAbilityByIdUrl(id: String): Url

        fun getSkillByNameUrl(name: String): Url

        fun getAbilityLimitedGroupByIdUrl(id: String): Url

    }

}