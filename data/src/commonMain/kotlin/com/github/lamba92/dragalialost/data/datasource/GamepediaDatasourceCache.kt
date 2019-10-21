package com.github.lamba92.dragalialost.data.datasource

import com.github.lamba92.dragalialost.data.rawresponses.*

interface GamepediaDatasourceCache : Cache {

    suspend fun getAdventurerById(id: String): AdventurerJSON?
    suspend fun getDragonById(id: String): DragonJSON?
    suspend fun getWyrmprintById(id: String): WyrmprintJSON?
    suspend fun getWeaponById(id: String): WeaponJSON?
    suspend fun getAbilityById(id: String): AbilityJSON?
    suspend fun getCoAbilityById(id: String): CoAbilityJSON?
    suspend fun getSkillByName(id: String): SkillJSON?
    suspend fun getAbilityLimitedGroupById(id: String): AbilityLimitedGroupJSON?

    suspend fun cacheAdventurerById(id: String, data: AdventurerJSON): Boolean
    suspend fun cacheDragonById(id: String, data: DragonJSON): Boolean
    suspend fun cacheWyrmprintById(id: String, data: WyrmprintJSON): Boolean
    suspend fun cacheWeaponById(id: String, data: WeaponJSON): Boolean
    suspend fun cacheAbilityById(id: String, data: AbilityJSON): Boolean
    suspend fun cacheCoAbilityById(id: String, data: CoAbilityJSON): Boolean
    suspend fun cacheSkillByName(id: String, data: SkillJSON): Boolean
    suspend fun cacheAbilityLimitedGroupById(id: String, data: AbilityLimitedGroupJSON): Boolean

}