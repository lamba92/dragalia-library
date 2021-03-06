package com.github.lamba92.dragalialost.core.datasource

import com.github.lamba92.dragalialost.core.utils.get
import com.github.lamba92.dragalialost.core.utils.getAndApply
import com.github.lamba92.dragalialost.core.utils.set
import com.github.lamba92.dragalialost.core.utils.toAtomicReference
import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasourceCache
import com.github.lamba92.dragalialost.data.datasource.queries.*
import com.github.lamba92.dragalialost.data.rawresponses.*

class InMemoryGamepediaCache : GamepediaDatasourceCache {

    // queries cache declarations
    private val adventurersQueryCache =
        mutableMapOf<Pair<AdventurersCargoQuery, Int>, List<AdventurerIdJSON>>().toAtomicReference()
    private val dragonsQueryCache =
        mutableMapOf<Pair<DragonsCargoQuery, Int>, List<String>>().toAtomicReference()
    private val wyrmprintsQueryCache =
        mutableMapOf<Pair<WyrmprintsCargoQuery, Int>, List<String>>().toAtomicReference()
    private val weaponsQueryCache =
        mutableMapOf<Pair<WeaponsCargoQuery, Int>, List<String>>().toAtomicReference()
    private val abilitiesQueryCache =
        mutableMapOf<Pair<AbilitiesCargoQuery, Int>, List<String>>().toAtomicReference()
    private val coAbilitiesQueryCache =
        mutableMapOf<Pair<CoAbilitiesCargoQuery, Int>, List<String>>().toAtomicReference()
    private val skillsQueryCache =
        mutableMapOf<Pair<SkillsCargoQuery, Int>, List<String>>().toAtomicReference()
    private val abilityLimitedGroupsQueryCache =
        mutableMapOf<Pair<AbilityLimitedGroupsCargoQuery, Int>, List<String>>().toAtomicReference()


    // entities cache declarations
    private val adventurersCache =
        mutableMapOf<Pair<String, String>, AdventurerJSON>().toAtomicReference()
    private val dragonsCache =
        mutableMapOf<String, DragonJSON>().toAtomicReference()
    private val wyrmprintsCache =
        mutableMapOf<String, WyrmprintJSON>().toAtomicReference()
    private val weaponsCache =
        mutableMapOf<String, WeaponJSON>().toAtomicReference()
    private val abilitiesCache =
        mutableMapOf<String, AbilityJSON>().toAtomicReference()
    private val coAbilitiesCache =
        mutableMapOf<String, CoAbilityJSON>().toAtomicReference()
    private val skillsCache =
        mutableMapOf<String, SkillJSON>().toAtomicReference()
    private val abilityLimitedGroupsCache =
        mutableMapOf<String, AbilityLimitedGroupJSON>().toAtomicReference()
    private val abilityGroupsCache =
        mutableMapOf<String, AbilityGroupJSON>().toAtomicReference()


    // images cache declarations
    private val adventurerIconsCache =
        mutableMapOf<Triple<String, String, Int>, ImageInfoJSON>().toAtomicReference()
    private val adventurerPortraitsCache =
        mutableMapOf<Triple<String, String, Int>, ImageInfoJSON>().toAtomicReference()
    private val dragonIconsCache =
        mutableMapOf<String, ImageInfoJSON>().toAtomicReference()
    private val dragonPortraitsCache =
        mutableMapOf<String, ImageInfoJSON>().toAtomicReference()
    private val wyrmprintIconsCache =
        mutableMapOf<Pair<String, Int>, ImageInfoJSON>().toAtomicReference()
    private val wyrmprintPortraitsCache =
        mutableMapOf<Pair<String, Int>, ImageInfoJSON>().toAtomicReference()
    private val abilityIconsCache =
        mutableMapOf<String, ImageInfoJSON>().toAtomicReference()
    private val skillIconsCache =
        mutableMapOf<String, ImageInfoJSON>().toAtomicReference()


    // queries cache retrieval implementations
    override suspend fun searchAdventurerIds(query: AdventurersCargoQuery, limit: Int) =
        adventurersQueryCache[query, limit]

    override suspend fun searchDragonIds(query: DragonsCargoQuery, limit: Int) =
        dragonsQueryCache[query, limit]

    override suspend fun searchWyrmprintIds(query: WyrmprintsCargoQuery, limit: Int) =
        wyrmprintsQueryCache[query, limit]

    override suspend fun searchWeaponIds(query: WeaponsCargoQuery, limit: Int) =
        weaponsQueryCache[query, limit]

    override suspend fun searchAbilityIds(query: AbilitiesCargoQuery, limit: Int) =
        abilitiesQueryCache[query, limit]

    override suspend fun searchCoAbilityIds(query: CoAbilitiesCargoQuery, limit: Int) =
        coAbilitiesQueryCache[query, limit]

    override suspend fun searchSkillIds(query: SkillsCargoQuery, limit: Int) =
        skillsQueryCache[query, limit]

    override suspend fun searchAbilityLimitedGroupIds(query: AbilityLimitedGroupsCargoQuery, limit: Int) =
        abilityLimitedGroupsQueryCache[query, limit]


    // entities cache retrieval implementations
    override suspend fun getAdventurerByIds(id: String, variationId: String) =
        adventurersCache[id, variationId]

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

    override suspend fun getSkillById(id: String) =
        skillsCache[id]

    override suspend fun getSkillByName(name: String): SkillJSON? {
        TODO("Not yet implemented")
    }

    override suspend fun getAbilityLimitedGroupById(id: String) =
        abilityLimitedGroupsCache[id]

    override suspend fun getAbilityGroupsByGroupId(id: String) =
        abilityGroupsCache[id]

    // images cache retrieval implementations
    override suspend fun getAdventurerIconById(id: String, variationId: String, rarity: Int) =
        adventurerIconsCache[id, variationId, rarity]

    override suspend fun getAdventurerPortraitById(id: String, variationId: String, rarity: Int) =
        adventurerPortraitsCache[id, variationId, rarity]

    override suspend fun getDragonIconById(id: String) =
        dragonIconsCache[id]

    override suspend fun getDragonPortraitById(id: String) =
        dragonPortraitsCache[id]

    override suspend fun getWyrmprintIconByIds(id: String, vestige: Int) =
        wyrmprintIconsCache[id, vestige]

    override suspend fun getWyrmprintPortraitByIds(id: String, vestige: Int) =
        wyrmprintPortraitsCache[id, vestige]

    override suspend fun getAbilityIconByFileName(fileName: String) =
        abilityIconsCache[fileName]

    override suspend fun getCoAbilityIconByFileName(fileName: String) =
        getAbilityIconByFileName(fileName)

    override suspend fun getSkillIconByIconName(fileName: String) =
        skillIconsCache[fileName]


    // entities cache save implementations
    override suspend fun cacheAdventurerByIds(id: String, variationId: String, data: AdventurerJSON): Boolean {
        adventurersCache[id, variationId] = data
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

    override suspend fun cacheSkillById(id: String, data: SkillJSON): Boolean {
        skillsCache[id] = data
        return true
    }

    override suspend fun cacheSkillByName(name: String, data: SkillJSON): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun cacheAbilityLimitedGroupById(id: String, data: AbilityLimitedGroupJSON): Boolean {
        abilityLimitedGroupsCache[id] = data
        return true
    }

    override suspend fun cacheAbilityGroupsByGroupId(groupId: String, data: AbilityGroupJSON): Boolean {
        abilityGroupsCache[groupId] = data
        return true
    }

    // queries cache save implementations
    override suspend fun cacheAdventurerCargoQuery(
        query: AdventurersCargoQuery,
        limit: Int,
        data: List<AdventurerIdJSON>
    ): Boolean {
        adventurersQueryCache[query, limit] = data
        return true
    }

    override suspend fun cacheDragonCargoQuery(query: DragonsCargoQuery, limit: Int, data: List<String>): Boolean {
        dragonsQueryCache[query, limit] = data
        return true
    }

    override suspend fun cacheWyrmprintCargoQuery(
        query: WyrmprintsCargoQuery,
        limit: Int,
        data: List<String>
    ): Boolean {
        wyrmprintsQueryCache[query, limit] = data
        return true
    }

    override suspend fun cacheWeaponCargoQuery(query: WeaponsCargoQuery, limit: Int, data: List<String>): Boolean {
        weaponsQueryCache[query, limit] = data
        return true
    }

    override suspend fun cacheAbilityCargoQuery(query: AbilitiesCargoQuery, limit: Int, data: List<String>): Boolean {
        coAbilitiesQueryCache[query, limit] = data
        return true
    }

    override suspend fun cacheCoAbilityCargoQuery(
        query: CoAbilitiesCargoQuery,
        limit: Int,
        data: List<String>
    ): Boolean {
        coAbilitiesQueryCache[query, limit] = data
        return true
    }

    override suspend fun cacheSkillCargoQuery(query: SkillsCargoQuery, limit: Int, data: List<String>): Boolean {
        skillsQueryCache[query, limit] = data
        return true
    }

    override suspend fun cacheAbilityLimitedGroupCargoQuery(
        query: AbilityLimitedGroupsCargoQuery,
        limit: Int,
        data: List<String>
    ): Boolean {
        abilityLimitedGroupsQueryCache[query, limit] = data
        return true
    }


    // images cache save implementations
    override suspend fun cacheAdventurerIconById(
        id: String,
        variationId: String,
        rarity: Int,
        data: ImageInfoJSON
    ): Boolean {
        adventurerIconsCache[id, variationId, rarity] = data
        return true
    }

    override suspend fun cacheAdventurerPortraitById(
        id: String,
        variationId: String,
        rarity: Int,
        data: ImageInfoJSON
    ): Boolean {
        adventurerPortraitsCache[id, variationId, rarity] = data
        return true
    }

    override suspend fun cacheDragonIconById(id: String, data: ImageInfoJSON): Boolean {
        dragonIconsCache[id] = data
        return true
    }

    override suspend fun cacheDragonPortraitById(id: String, data: ImageInfoJSON): Boolean {
        dragonPortraitsCache[id] = data
        return true
    }

    override suspend fun cacheWyrmprintIconByIds(id: String, vestige: Int, data: ImageInfoJSON): Boolean {
        wyrmprintIconsCache[id, vestige] = data
        return true
    }

    override suspend fun cacheWyrmprintPortraitByIds(id: String, vestige: Int, data: ImageInfoJSON): Boolean {
        wyrmprintPortraitsCache[id, vestige] = data
        return true
    }

    override suspend fun cacheAbilityIconByFileName(fileName: String, data: ImageInfoJSON): Boolean {
        abilityIconsCache[fileName] = data
        return true
    }

    override suspend fun cacheCoAbilityIconByFileName(fileName: String, data: ImageInfoJSON) =
        cacheAbilityIconByFileName(fileName, data)

    override suspend fun cacheSkillIconByIconName(fileName: String, data: ImageInfoJSON): Boolean {
        skillIconsCache[fileName] = data
        return true
    }

    override suspend fun invalidateCache() =
        listOf(
            adventurersCache, dragonsCache, weaponsCache, abilitiesCache, coAbilitiesCache, skillsCache,
            coAbilitiesCache, adventurersQueryCache, dragonsQueryCache, wyrmprintsQueryCache, weaponsQueryCache,
            abilitiesQueryCache, coAbilitiesQueryCache, skillsQueryCache, abilityLimitedGroupsQueryCache
        ).forEach { it.getAndApply { clear() } }

}
