package com.github.lamba92.dragalia.datasource

import com.github.lamba92.dragalia.datasource.queries.*
import com.github.lamba92.dragalia.rawresponses.*
import io.ktor.http.URLProtocol
import io.ktor.http.Url

interface GamepediaDatasource {

    suspend fun searchAdventurers(
        query: AdventurersCargoQuery,
        limit: Int = 500
    ): AdventurerCargoJSON

    suspend fun searchDragons(
        query: DragonsCargoQuery,
        limit: Int = 500
    ): DragonCargoJSON

    suspend fun searchWyrmprints(
        query: WyrmprintsCargoQuery,
        limit: Int = 500
    ): WyrmprintCargoJSON

    suspend fun searchWeapons(
        query: WeaponsCargoQuery,
        limit: Int = 500
    ): WeaponCargoJSON

    suspend fun searchAbilities(
        query: AbilitiesCargoQuery,
        limit: Int = 500
    ): AbilityCargoJSON

    suspend fun searchCoAbilities(
        query: CoAbilitiesCargoQuery,
        limit: Int = 500
    ): CoAbilityCargoJSON

    suspend fun searchSkills(
        query: SkillsCargoQuery,
        limit: Int = 500
    ): SkillCargoJSON

    suspend fun searchAbilityLimitedGroups(
        query: AbilityLimitedGroupsCargoQuery,
        limit: Int = 500
    ): AbilityLimitedGroupCargoJSON

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
            limit: Int = 500
        ): Url

        fun searchDragonsUrl(
            name: String? = null,
            element: String? = null,
            rarity: Int? = null,
            limit: Int = 500
        ): Url

        fun searchWyrmprintsUrl(
            name: String? = null,
            element: String? = null,
            rarity: Int? = null,
            limit: Int = 500
        ): Url

        fun searchWeaponsUrl(
            name: String? = null,
            element: String? = null,
            rarity: Int? = null,
            limit: Int = 500
        ): Url

        fun searchAbilitiesUrl(
            name: String? = null,
            limit: Int = 500
        ): Url

        fun searchCoAbilitiesUrl(
            name: String? = null,
            limit: Int = 500
        ): Url

        fun searchSkillsUrl(
            name: String? = null,
            limit: Int = 500
        ): Url

        fun searchAbilityLimitedGroupsUrl(
            name: String? = null,
            isEffectMIx: Boolean? = null,
            maxLimitedValue: Int? = null,
            abilityLimitedText: String? = null,
            limit: Int = 500
        ): Url

    }

}