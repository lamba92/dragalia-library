package com.github.lamba92.dragalialost.core.datasource

import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasourceCache
import com.github.lamba92.dragalialost.data.rawresponses.*

class InMemoryGamepediaCache : GamepediaDatasourceCache {

    private val adventurersCache = mutableMapOf<String, AdventurerJSON>()
    private val dragonsCache = mutableMapOf<String, DragonJSON>()
    private val wyrmprintsCache = mutableMapOf<String, WyrmprintJSON>()
    private val weaponsCache = mutableMapOf<String, WeaponJSON>()
    private val abilitiesCache = mutableMapOf<String, AbilityJSON>()
    private val coAbilitiesCache = mutableMapOf<String, CoAbilityJSON>()
    private val skillsCache = mutableMapOf<String, SkillJSON>()
    private val abilityLimitedGroupsCache = mutableMapOf<String, AbilityLimitedGroupJSON>()

    override suspend fun getAdventurerById(id: String) =
        adventurersCache[id]

    override suspend fun getDragonById(id: String) =
        dragonsCache[id]

    override suspend fun getWyrmprintById(id: String) =
        wyrmprintsCache[id]

    override suspend fun getWeaponById(id: String) =
        weaponsCache[id]

    override suspend fun getAbilityById(id: String) =
        abilitiesCache[id]

    override suspend fun getCoAbilityById(id: String) =
        coAbilitiesCache[id]

    override suspend fun getSkillByName(id: String) =
        skillsCache[id]

    override suspend fun getAbilityLimitedGroupById(id: String) =
        abilityLimitedGroupsCache[id]

    override suspend fun cacheAdventurerById(id: String, data: AdventurerJSON): Boolean {
        adventurersCache[id] = data
        return true
    }

    override suspend fun cacheDragonById(id: String, data: DragonJSON): Boolean {
        dragonsCache[id] = data
        return true
    }

    override suspend fun cacheWyrmprintById(id: String, data: WyrmprintJSON): Boolean {
        wyrmprintsCache[id] = data
        return true
    }

    override suspend fun cacheWeaponById(id: String, data: WeaponJSON): Boolean {
        weaponsCache[id] = data
        return true
    }

    override suspend fun cacheAbilityById(id: String, data: AbilityJSON): Boolean {
        abilitiesCache[id] = data
        return true
    }

    override suspend fun cacheCoAbilityById(id: String, data: CoAbilityJSON): Boolean {
        coAbilitiesCache[id] = data
        return true
    }

    override suspend fun cacheSkillByName(id: String, data: SkillJSON): Boolean {
        skillsCache[id] = data
        return true
    }

    override suspend fun cacheAbilityLimitedGroupById(id: String, data: AbilityLimitedGroupJSON): Boolean {
        abilityLimitedGroupsCache[id] = data
        return true
    }

    override fun invalidateCache() {
        adventurersCache.clear()
        dragonsCache.clear()
        weaponsCache.clear()
        abilitiesCache.clear()
        coAbilitiesCache.clear()
        skillsCache.clear()
        coAbilitiesCache.clear()
    }
}