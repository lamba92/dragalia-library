package com.github.lamba92.dragalia.datasource

import com.github.lamba92.dragalia.rawresponses.*
import io.ktor.http.URLProtocol
import io.ktor.http.Url

interface GamepediaDatasource {

    suspend fun searchAdventurers(
        name: String? = null,
        weaponType: String? = null,
        element: String? = null,
        heroClass: String? = null,
        rarity: Int? = null,
        limit: Int = 10
    ): AdventurerCargoJSON

    suspend fun searchDragons(
        name: String? = null,
        element: String? = null,
        rarity: Int? = null,
        limit: Int = 10
    ): DragonCargoJSON

    suspend fun searchWyrmprints(
        name: String? = null,
        element: String? = null,
        type: String? = null,
        rarity: Int? = null,
        limit: Int = 10
    ): WyrmprintCargoJSON

    suspend fun searchWeapons(
        name: String? = null,
        element: String? = null,
        rarity: Int? = null,
        limit: Int = 10
    ): WeaponCargoJSON

    suspend fun searchAbilities(
        name: String? = null,
        limit: Int = 10
    ): AbilityCargoJSON

    suspend fun searchCoAbilities(
        name: String? = null,
        limit: Int = 10
    ): CoAbilityCargoJSON

    suspend fun searchSkills(
        name: String? = null,
        limit: Int = 10
    ): SkillCargoJSON

    suspend fun searchAbilityLimitedGroups(
        name: String? = null,
        isEffectMIx: Boolean? = null,
        maxLimitedValue: Int? = null,
        abilityLimitedText: String? = null,
        limit: Int = 10
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
            limit: Int = 10
        ): Url

        fun searchDragonsUrl(
            name: String? = null,
            element: String? = null,
            rarity: Int? = null,
            limit: Int = 10
        ): Url

        fun searchWyrmprintsUrl(
            name: String? = null,
            element: String? = null,
            rarity: Int? = null,
            limit: Int = 10
        ): Url

        fun searchWeaponsUrl(
            name: String? = null,
            element: String? = null,
            rarity: Int? = null,
            limit: Int = 10
        ): Url

        fun searchAbilitiesUrl(
            name: String? = null,
            limit: Int = 10
        ): Url

        fun searchCoAbilitiesUrl(
            name: String? = null,
            limit: Int = 10
        ): Url

        fun searchSkillsUrl(
            name: String? = null,
            limit: Int = 10
        ): Url

        fun searchAbilityLimitedGroupsUrl(
            name: String? = null,
            isEffectMIx: Boolean? = null,
            maxLimitedValue: Int? = null,
            abilityLimitedText: String? = null,
            limit: Int = 10
        ): Url

    }

}