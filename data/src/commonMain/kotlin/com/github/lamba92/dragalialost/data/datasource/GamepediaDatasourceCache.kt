package com.github.lamba92.dragalialost.data.datasource

import com.github.lamba92.dragalialost.data.rawresponses.*

interface GamepediaDatasourceCache : Cache {

    suspend fun getAdventurersById(id: String): AdventurerJSON?
    suspend fun getDragonsById(id: String): DragonJSON?
    suspend fun getWyrmprintsById(id: String): WyrmprintJSON?
    suspend fun getWeaponsById(id: String): WeaponJSON?
    suspend fun getAbilitiesById(id: String): AbilityJSON?
    suspend fun getCoAbilitiesById(id: String): CoAbilityJSON?
    suspend fun getSkillsById(id: String): SkillJSON?
    suspend fun getAbilityLimitedGroupsById(id: String): AbilityLimitedGroupJSON?

    suspend fun cacheAdventurersById(id: String, data: AdventurerJSON): Boolean
    suspend fun cacheDragonsById(id: String, data: DragonJSON): Boolean
    suspend fun cacheWyrmprintsById(id: String, data: WyrmprintJSON): Boolean
    suspend fun cacheWeaponsById(id: String, data: WeaponJSON): Boolean
    suspend fun cacheAbilitiesById(id: String, data: AbilityJSON): Boolean
    suspend fun cacheCoAbilitiesById(id: String, data: CoAbilityJSON): Boolean
    suspend fun cacheSkillsById(id: String, data: SkillJSON): Boolean
    suspend fun cacheAbilityLimitedGroupsById(id: String, data: AbilityLimitedGroupJSON): Boolean

}