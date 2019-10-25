package com.github.lamba92.dragalialost.data.datasource

import com.github.lamba92.dragalialost.data.datasource.queries.*
import com.github.lamba92.dragalialost.data.rawresponses.*
import io.ktor.http.URLProtocol
import io.ktor.http.Url

interface GamepediaDatasource {

    suspend fun searchAdventurerIds(query: AdventurersCargoQuery, limit: Int = 500): List<AdventurerIdJSON>
    suspend fun searchDragonIds(query: DragonsCargoQuery, limit: Int = 500): List<String>
    suspend fun searchWyrmprintIds(query: WyrmprintsCargoQuery, limit: Int = 500): List<String>
    suspend fun searchWeaponIds(query: WeaponsCargoQuery, limit: Int = 500): List<String>
    suspend fun searchAbilityIds(query: AbilitiesCargoQuery, limit: Int = 500): List<String>
    suspend fun searchCoAbilityIds(query: CoAbilitiesCargoQuery, limit: Int = 500): List<String>
    suspend fun searchSkillIds(query: SkillsCargoQuery, limit: Int = 500): List<String>
    suspend fun searchAbilityLimitedGroupIds(query: AbilityLimitedGroupsCargoQuery, limit: Int = 500): List<String>

    suspend fun getAdventurerByIds(id: String, variationId: String): AdventurerJSON
    suspend fun getDragonById(id: String): DragonJSON
    suspend fun getWyrmprintById(id: String): WyrmprintJSON
    suspend fun getWeaponsById(id: String): WeaponJSON
    suspend fun getAbilityById(id: String): AbilityJSON
    suspend fun getCoAbilityById(id: String): CoAbilityJSON
    suspend fun getSkillByName(id: String): SkillJSON
    suspend fun getAbilityLimitedGroupsById(id: String): AbilityLimitedGroupJSON
    suspend fun getAbilityGroupsByGroupId(groupId: String): AbilityGroupJSON

    suspend fun getAdventurerIconById(id: String, variationId: String, rarity: Int): ImageInfoJSON
    suspend fun getAdventurerPortraitById(id: String, variationId: String, rarity: Int): ImageInfoJSON
    suspend fun getDragonIconById(id: String): ImageInfoJSON
    suspend fun getDragonPortraitById(id: String): ImageInfoJSON
    suspend fun getWyrmprintIconByIds(id: String, vestige: Int): ImageInfoJSON
    suspend fun getWyrmprintPortraitByIds(id: String, vestige: Int): ImageInfoJSON
    suspend fun getAbilityIconByFileName(fileName: String): ImageInfoJSON
    suspend fun getCoAbilityIconByFileName(fileName: String): ImageInfoJSON
    suspend fun getSkillIconByIconName(fileName: String): ImageInfoJSON

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
            rarity: Int? = null,
            limit: Int = 500
        ): Url

        fun searchDragonIdsUrl(
            name: String? = null,
            element: String? = null,
            rarity: Int? = null,
            limit: Int = 500
        ): Url

        fun searchWyrmprintIdsUrl(
            name: String? = null,
            rarity: Int? = null,
            limit: Int = 500
        ): Url

        fun searchWeaponIdsUrl(
            name: String? = null,
            element: String? = null,
            rarity: Int? = null,
            limit: Int = 500
        ): Url

        fun searchAbilityIdsUrl(name: String? = null, limit: Int = 500): Url
        fun searchCoAbilityIdsUrl(name: String? = null, limit: Int = 500): Url
        fun searchSkillIdsUrl(name: String? = null, limit: Int = 500): Url

        fun searchAbilityLimitedGroupIdsUrl(
            name: String? = null,
            isEffectMIx: Boolean? = null,
            maxLimitedValue: Int? = null,
            abilityLimitedText: String? = null,
            limit: Int = 500
        ): Url

        fun getAdventurerByIdsUrl(id: String, variationId: String): Url
        fun getDragonByIdUrl(id: String): Url
        fun getWyrmprintByIdUrl(id: String): Url
        fun getWeaponByIdUrl(id: String): Url
        fun getAbilityByIdUrl(id: String): Url
        fun getCoAbilityByIdUrl(id: String): Url
        fun getSkillByNameUrl(name: String): Url
        fun getAbilityLimitedGroupByIdUrl(id: String): Url
        fun getAbilityGroupByGroupIdUrl(groupId: String): Url

        fun getAdventurerIconByIdUrl(id: String, variationId: String, rarity: Int): Url
        fun getAdventurerPortraitByIdUrl(id: String, variationId: String, rarity: Int): Url
        fun getAbilityIconByFileNameUrl(fileName: String): Url
        fun getCoAbilityIconByFileNameUrl(fileName: String): Url
        fun getDragonIconByIdUrl(id: String): Url
        fun getDragonPortraitByIdUrl(id: String): Url
        fun getWyrmprintIconByIdsUrl(id: String, vestige: Int): Url
        fun getWyrmprintPortraitByIdsUrl(id: String, vestige: Int): Url
        fun getSkillIconByIconNameUrl(fileName: String): Url

    }

}