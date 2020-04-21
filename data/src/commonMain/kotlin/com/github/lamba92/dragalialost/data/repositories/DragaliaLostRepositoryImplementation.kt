package com.github.lamba92.dragalialost.data.repositories

import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasource
import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasourceCache
import com.github.lamba92.dragalialost.data.mappers.*
import com.github.lamba92.dragalialost.data.utils.*
import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQuery
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQuery
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQuery
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class DragaliaLostRepositoryImplementation(
    private val datasource: GamepediaDatasource,
    private val cache: GamepediaDatasourceCache?,
    private val adventurersQueryMapper: AdventurersQueryMapper,
    private val wyrmprintsQueryMapper: WyrmprintsQueryMapper,
    private val dragonsQueryMapper: DragonsQueryMapper,
    private val dragonMapper: DragonMapper,
    private val adventurerMapper: AdventurerMapper,
    private val wyrmprintMapper: WyrmprintMapper
) : DragaliaLostRepository {

    @ExperimentalCoroutinesApi
    @FlowPreview
    override suspend fun searchAdventurers(query: AdventurersQuery, limit: Int) =
        adventurersQueryMapper.toRemote(query)
            .asFlow()
            .map { dsQuery ->
                cache?.searchAdventurerIds(dsQuery, limit) ?: datasource.searchAdventurerIds(dsQuery, limit).also {
                    cache?.cacheAdventurerCargoQuery(dsQuery, limit, it)
                }
            }
            .flattenConcatIterable()
            .map { (id, variationId) ->
                cache?.getAdventurerByIds(id, variationId) ?: datasource.getAdventurerByIds(id, variationId).also {
                    cache?.cacheAdventurerByIds(id, variationId, it)
                }
            }
            .scopedMap { json ->
                with(json) {
                    val a11 = getAbilityDataAsync(Abilities11)
                    val a12 = getAbilityDataAsync(Abilities12)
                    val a13 = Abilities13.ifIsNotBlankOrZero { getAbilityDataAsync(it) }

                    val a21 = getAbilityDataAsync(Abilities21)
                    val a22 = getAbilityDataAsync(Abilities22)
                    val a23 = Abilities23.ifIsNotBlankOrZero { getAbilityDataAsync(it) }

                    val a31 = getAbilityDataAsync(Abilities31)
                    val a32 = Abilities32.ifIsNotBlankOrZero { getAbilityDataAsync(it) }
                    val a33 = Abilities33.ifIsNotBlankOrZero { getAbilityDataAsync(it) }

                    val s1 = getSkillDataByNameAsync(Skill1Name)
                    val s2 = getSkillDataByNameAsync(Skill2Name)

                    val coa1 = getCoAbilityDataAsync(ExAbilityData1)
                    val coa2 = getCoAbilityDataAsync(ExAbilityData2)
                    val coa3 = getCoAbilityDataAsync(ExAbilityData3)
                    val coa4 = getCoAbilityDataAsync(ExAbilityData4)
                    val coa5 = getCoAbilityDataAsync(ExAbilityData5)

                    val images = (Rarity.toInt()..5).map {
                        async { getAndCacheAdventurerPortraitImageInfoByIds(Id, VariationId, it) }
                    }
                    val icons = (Rarity.toInt()..5).map {
                        async { getAndCacheAdventurerIconImageInfoByIds(Id, VariationId, it) }
                    }

                    AdventurerMapper.Params(
                        json, a11.await(), a12.await(), a13?.await(), a21.await(), a22.await(), a23?.await(),
                        a31.await(), a32?.await(), a33?.await(), coa1.await(), coa2.await(), coa3.await(),
                        coa4.await(), coa5.await(), s1.await(), s2.await(), images.awaitAll(),
                        icons.awaitAll()
                    )
                }
            }
            .map { adventurerMapper(it) }
            .filter { it in query }
            .toList()

    @ExperimentalCoroutinesApi
    @FlowPreview
    override suspend fun searchDragons(query: DragonsQuery, limit: Int) =
        dragonsQueryMapper.toRemote(query)
            .asFlow()
            .map { dsQuery ->
                cache?.searchDragonIds(dsQuery, limit) ?: datasource.searchDragonIds(dsQuery, limit).also {
                    cache?.cacheDragonCargoQuery(dsQuery, limit, it)
                }
            }
            .flattenConcatIterable()
            .distinctUntilChanged()
            .map { dragonId ->
                cache?.getDragonById(dragonId) ?: datasource.getDragonById(dragonId)
                    .also { cache?.cacheDragonById(dragonId, it) }
            }
            .scopedMap { json ->
                with(json) {
                    val a11 = getAbilityDataAsync(Abilities11)
                    val a12 = getAbilityDataAsync(Abilities12)

                    val a21 = Abilities21.ifIsNotBlankOrZero { getAbilityDataAsync(it) }
                    val a22 = Abilities22.ifIsNotBlankOrZero { getAbilityDataAsync(it) }

                    val s1 = getSkillDataByNameAsync(SkillName)

                    val icon = async { getAndCacheDragonIconImageInfoById(BaseId) }
                    val portrait = async { getAndCacheDragonPortraitImageInfoById(BaseId) }

                    DragonMapper.Params(
                        json, a11.await(), a12.await(), a21?.await(), a22?.await(),
                        s1.await(), icon.await(), portrait.await()
                    )
                }
            }
//            .catch {
//                println("A dragon errored: $it")
//            }
            .map { dragonMapper(it) }
            .filter { it in query }
            .toList()

    @ExperimentalCoroutinesApi
    @FlowPreview
    override suspend fun searchWyrmprints(query: WyrmprintsQuery, limit: Int) =
        wyrmprintsQueryMapper.toRemote(query)
            .asFlow()
            .map { dsQuery ->
                cache?.searchWyrmprintIds(dsQuery, limit) ?: datasource.searchWyrmprintIds(dsQuery, limit).also {
                    cache?.cacheWyrmprintCargoQuery(dsQuery, limit, it)
                }
            }
            .flattenMergeIterable()
            .distinctUntilChanged()
            .map { id ->
                cache?.getWyrmprintById(id) ?: datasource.getWyrmprintById(id)
                    .also { cache?.cacheWyrmprintById(id, it) }
            }
            .scopedMap { json ->
                with(json) {
                    val a11 = getAbilityDataAsync(Abilities11)
                    val a12 = getAbilityDataAsync(Abilities12)
                    val a13 = getAbilityDataAsync(Abilities13)

                    val a21 = Abilities21.ifIsNotBlankOrZero { getAbilityDataAsync(it) }
                    val a22 = Abilities22.ifIsNotBlankOrZero { getAbilityDataAsync(it) }
                    val a23 = Abilities23.ifIsNotBlankOrZero { getAbilityDataAsync(it) }

                    val a31 = Abilities31.ifIsNotBlankOrZero { getAbilityDataAsync(it) }
                    val a32 = Abilities32.ifIsNotBlankOrZero { getAbilityDataAsync(it) }
                    val a33 = Abilities33.ifIsNotBlankOrZero { getAbilityDataAsync(it) }

                    val icon1 = async { getAndCacheWyrmprintIconImageInfoById(BaseId, 1) }
                    val icon2 = async { getAndCacheWyrmprintIconImageInfoById(BaseId, 2) }
                    val artwork1 = async { getAndCacheWyrmprintIconPortraitInfoById(BaseId, 1) }
                    val artwork2 = async { getAndCacheWyrmprintIconPortraitInfoById(BaseId, 2) }

                    WyrmprintMapper.Params(
                        json, a11.await(), a12.await(), a13.await(), a21?.await(), a22?.await(),
                        a23?.await(), a31?.await(), a32?.await(), a33?.await(), icon1.await(),
                        icon2.await(), artwork1.await(), artwork2.await()
                    )
                }
            }
//            .catch {
//                println("A wyrmprint errored: $it")
//            }
            .map { wyrmprintMapper(it) }
            .filter { it in query }
            .toList()

    private fun CoroutineScope.getAbilityDataAsync(id: String) = async {
        getAndCacheAbilityById(id) withPair {
            getAndCacheAbilityIconImageInfoByFileName(AbilityIconName) to getAndCacheAbilityGroupByGroupId(AbilityGroup)
        }
    }

    private fun CoroutineScope.getCoAbilityDataAsync(id: String) = async {
        getAndCacheCoAbilityById(id).with { getAndCacheCoAbilityIconImageInfoByFileName(AbilityIconName) }
    }

    private fun CoroutineScope.getSkillDataByIdAsync(id: String) = async {
        getAndCacheSkillById(id) with {
            Triple(
                async { getAndCacheSkillIconImageInfoByFileName(SkillLv1IconName) },
                async { getAndCacheSkillIconImageInfoByFileName(SkillLv2IconName) },
                async { getAndCacheSkillIconImageInfoByFileName(SkillLv3IconName) }
            ).await()
        }
    }

    private fun CoroutineScope.getSkillDataByNameAsync(name: String) = async {
        getAndCacheSkillByName(name) with {
            Triple(
                async { getAndCacheSkillIconImageInfoByFileName(SkillLv1IconName) },
                async { getAndCacheSkillIconImageInfoByFileName(SkillLv2IconName) },
                async { getAndCacheSkillIconImageInfoByFileName(SkillLv3IconName) }
            ).await()
        }
    }

    private suspend fun getAndCacheAbilityById(abilityId: String) = cache?.getAbilityById(abilityId)
        ?: datasource.getAbilityById(abilityId).also { cache?.cacheAbilityById(abilityId, it) }

    private suspend fun getAndCacheSkillById(skillId: String) = cache?.getSkillById(skillId)
        ?: datasource.getSkillById(skillId).also { cache?.cacheSkillById(skillId, it) }

    private suspend fun getAndCacheSkillByName(skillId: String) = cache?.getSkillByName(skillId)
        ?: datasource.getSkillByName(skillId).also { cache?.cacheSkillByName(skillId, it) }

    private suspend fun getAndCacheCoAbilityById(coAbilityId: String) = cache?.getCoAbilityById(coAbilityId)
        ?: datasource.getCoAbilityById(coAbilityId).also { cache?.cacheCoAbilityById(coAbilityId, it) }

    private suspend fun getAndCacheAbilityIconImageInfoByFileName(fileName: String) =
        cache?.getAbilityIconByFileName(fileName) ?: datasource.getAbilityIconByFileName(fileName)
            ?.also { cache?.cacheAbilityIconByFileName(fileName, it) }

    private suspend fun getAndCacheCoAbilityIconImageInfoByFileName(fileName: String) =
        cache?.getCoAbilityIconByFileName(fileName) ?: datasource.getCoAbilityIconByFileName(fileName)
            ?.also { cache?.cacheCoAbilityIconByFileName(fileName, it) }

    private suspend fun getAndCacheSkillIconImageInfoByFileName(fileName: String) =
        cache?.getSkillIconByIconName(fileName) ?: datasource.getSkillIconByIconName(fileName)
            ?.also { cache?.cacheSkillIconByIconName(fileName, it) }

    private suspend fun getAndCacheAdventurerPortraitImageInfoByIds(id: String, variationId: String, rarity: Int) =
        rarity to (cache?.getAdventurerPortraitById(id, variationId, rarity)
            ?: datasource.getAdventurerPortraitById(id, variationId, rarity)
                ?.also { cache?.cacheAdventurerPortraitById(id, variationId, rarity, it) })

    private suspend fun getAndCacheAdventurerIconImageInfoByIds(id: String, variationId: String, rarity: Int) =
        rarity to (cache?.getAdventurerIconById(id, variationId, rarity)
            ?: datasource.getAdventurerIconById(id, variationId, rarity)
                ?.also { cache?.cacheAdventurerIconById(id, variationId, rarity, it) })

    private suspend fun getAndCacheDragonPortraitImageInfoById(id: String) =
        cache?.getDragonPortraitById(id) ?: datasource.getDragonPortraitById(id)
            ?.also { cache?.cacheDragonPortraitById(id, it) }

    private suspend fun getAndCacheDragonIconImageInfoById(id: String) =
        cache?.getDragonIconById(id) ?: datasource.getDragonIconById(id)
            ?.also { cache?.cacheDragonIconById(id, it) }

    private suspend fun getAndCacheWyrmprintIconImageInfoById(id: String, vestige: Int) =
        cache?.getWyrmprintIconByIds(id, vestige) ?: datasource.getWyrmprintIconByIds(id, vestige)
            ?.also { cache?.cacheWyrmprintIconByIds(id, vestige, it) }

    private suspend fun getAndCacheWyrmprintIconPortraitInfoById(id: String, vestige: Int) =
        cache?.getWyrmprintPortraitByIds(id, vestige) ?: datasource.getWyrmprintPortraitByIds(id, vestige)
            ?.also { cache?.cacheWyrmprintPortraitByIds(id, vestige, it) }

    private suspend fun getAndCacheAbilityGroupByGroupId(groupId: String) =
        cache?.getAbilityGroupsByGroupId(groupId) ?: datasource.getAbilityGroupsByGroupId(groupId)
            .also { cache?.cacheAbilityGroupsByGroupId(groupId, it) }

}
