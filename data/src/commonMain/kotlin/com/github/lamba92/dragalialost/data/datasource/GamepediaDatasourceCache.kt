package com.github.lamba92.dragalialost.data.datasource

import com.github.lamba92.dragalialost.data.datasource.queries.*
import com.github.lamba92.dragalialost.data.rawresponses.*

interface GamepediaDatasourceCache : Cache {

    suspend fun searchAdventurerIds(query: AdventurersCargoQuery, limit: Int = 500): List<AdventurerIdJSON>?
    suspend fun searchDragonIds(query: DragonsCargoQuery, limit: Int = 500): List<String>?
    suspend fun searchWyrmprintIds(query: WyrmprintsCargoQuery, limit: Int = 500): List<String>?
    suspend fun searchWeaponIds(query: WeaponsCargoQuery, limit: Int = 500): List<String>?
    suspend fun searchAbilityIds(query: AbilitiesCargoQuery, limit: Int = 500): List<String>?
    suspend fun searchCoAbilityIds(query: CoAbilitiesCargoQuery, limit: Int = 500): List<String>?
    suspend fun searchSkillIds(query: SkillsCargoQuery, limit: Int = 500): List<String>?
    suspend fun searchAbilityLimitedGroupIds(query: AbilityLimitedGroupsCargoQuery, limit: Int = 500): List<String>?

    suspend fun getAdventurerByIds(id: String, variationId: String): AdventurerJSON?
    suspend fun getDragonById(id: String): DragonJSON?
    suspend fun getWyrmprintById(id: String): WyrmprintJSON?
    suspend fun getWeaponById(id: String): WeaponJSON?
    suspend fun getAbilityById(id: String): AbilityJSON?
    suspend fun getCoAbilityById(id: String): CoAbilityJSON?
    suspend fun getSkillByName(id: String): SkillJSON?
    suspend fun getAbilityLimitedGroupById(id: String): AbilityLimitedGroupJSON?

    suspend fun getAdventurerIconById(id: String, variationId: String, rarity: Int): ImageInfoJSON?
    suspend fun getAdventurerPortraitById(id: String, variationId: String, rarity: Int): ImageInfoJSON?
    suspend fun getDragonIconById(id: String): ImageInfoJSON?
    suspend fun getDragonPortraitById(id: String): ImageInfoJSON?
    suspend fun getWyrmprintIconByIds(id: String, vestige: Int): ImageInfoJSON?
    suspend fun getWyrmprintPortraitByIds(id: String, vestige: Int): ImageInfoJSON?
    suspend fun getAbilityIconByFileName(fileName: String): ImageInfoJSON?
    suspend fun getCoAbilityIconByFileName(fileName: String): ImageInfoJSON?
    suspend fun getSkillIconByIconName(fileName: String): ImageInfoJSON?

    suspend fun cacheAdventurerCargoQuery(
        query: AdventurersCargoQuery,
        limit: Int,
        data: List<AdventurerIdJSON>
    ): Boolean
    suspend fun cacheDragonCargoQuery(query: DragonsCargoQuery, limit: Int, data: List<String>): Boolean
    suspend fun cacheWyrmprintCargoQuery(query: WyrmprintsCargoQuery, limit: Int, data: List<String>): Boolean
    suspend fun cacheWeaponCargoQuery(query: WeaponsCargoQuery, limit: Int, data: List<String>): Boolean
    suspend fun cacheAbilityCargoQuery(query: AbilitiesCargoQuery, limit: Int, data: List<String>): Boolean
    suspend fun cacheCoAbilityCargoQuery(query: CoAbilitiesCargoQuery, limit: Int, data: List<String>): Boolean
    suspend fun cacheSkillCargoQuery(query: SkillsCargoQuery, limit: Int, data: List<String>): Boolean
    suspend fun cacheAbilityLimitedGroupCargoQuery(
        query: AbilityLimitedGroupsCargoQuery,
        limit: Int,
        data: List<String>
    ): Boolean

    suspend fun cacheAdventurerByIds(id: String, variationId: String, data: AdventurerJSON): Boolean
    suspend fun cacheDragonById(id: String, data: DragonJSON): Boolean
    suspend fun cacheWyrmprintById(id: String, data: WyrmprintJSON): Boolean
    suspend fun cacheWeaponById(id: String, data: WeaponJSON): Boolean
    suspend fun cacheAbilityById(id: String, data: AbilityJSON): Boolean
    suspend fun cacheCoAbilityById(id: String, data: CoAbilityJSON): Boolean
    suspend fun cacheSkillByName(id: String, data: SkillJSON): Boolean
    suspend fun cacheAbilityLimitedGroupById(id: String, data: AbilityLimitedGroupJSON): Boolean

    suspend fun cacheAdventurerIconById(id: String, variationId: String, rarity: Int, data: ImageInfoJSON): Boolean
    suspend fun cacheAdventurerPortraitById(id: String, variationId: String, rarity: Int, data: ImageInfoJSON): Boolean
    suspend fun cacheDragonIconById(id: String, data: ImageInfoJSON): Boolean
    suspend fun cacheDragonPortraitById(id: String, data: ImageInfoJSON): Boolean
    suspend fun cacheWyrmprintIconByIds(id: String, vestige: Int, data: ImageInfoJSON): Boolean
    suspend fun cacheWyrmprintPortraitByIds(id: String, vestige: Int, data: ImageInfoJSON): Boolean
    suspend fun cacheAbilityIconByFileName(fileName: String, data: ImageInfoJSON): Boolean
    suspend fun cacheCoAbilityIconByFileName(fileName: String, data: ImageInfoJSON): Boolean
    suspend fun cacheSkillIconByIconName(fileName: String, data: ImageInfoJSON): Boolean

}