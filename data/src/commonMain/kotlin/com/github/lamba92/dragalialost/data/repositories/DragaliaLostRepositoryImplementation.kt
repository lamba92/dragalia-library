package com.github.lamba92.dragalialost.data.repositories

import com.github.aakira.napier.Napier
import com.github.lamba92.dragalialost.data.DragaliaError
import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasource
import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasourceCache
import com.github.lamba92.dragalialost.data.mappers.*
import com.github.lamba92.dragalialost.data.rawresponses.AdventurerIdJSON
import com.github.lamba92.dragalialost.data.rawresponses.AdventurerJSON
import com.github.lamba92.dragalialost.data.rawresponses.DragonJSON
import com.github.lamba92.dragalialost.data.rawresponses.WyrmprintJSON
import com.github.lamba92.dragalialost.data.utils.*
import com.github.lamba92.dragalialost.domain.entities.DragaliaId
import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepositoryCache
import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQuery
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQuery
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*

@Suppress("EXPERIMENTAL_API_USAGE")
class DragaliaLostRepositoryImplementation(
    private val datasource: GamepediaDatasource,
    private val dsCache: GamepediaDatasourceCache?,
    private val repoCache: DragaliaLostRepositoryCache?,
    private val adventurersQueryMapper: AdventurersQueryMapper,
    private val wyrmprintsQueryMapper: WyrmprintsQueryMapper,
    private val dragonsQueryMapper: DragonsQueryMapper,
    private val dragonMapper: DragonMapper,
    private val adventurerMapper: AdventurerMapper,
    private val wyrmprintMapper: WyrmprintMapper
) : DragaliaLostRepository {

    override suspend fun getAdventurerById(id: DragaliaId) = try {
        repoCache?.getAdventurerById(id) ?: getAndCacheAdventurerJson(id)
            .also { repoCache?.cache(it) }
    } catch (e: Throwable) {
        throw DragaliaError.AdventurerAssemblingException(id, e)
    }

    override suspend fun getDragonById(id: DragaliaId) = try {
        repoCache?.getDragonById(id) ?: getAndCacheDragonJson(id)
            .also { repoCache?.cache(it) }
    } catch (e: Throwable) {
        throw DragaliaError.DragonAssemblingException(id, e)
    }

    override suspend fun getWyrmprintById(id: DragaliaId) = try {
        repoCache?.getWyrmprintById(id) ?: getAndCacheWyrmprintJson(id)
            .also { repoCache?.cache(it) }
    } catch (e: Throwable) {
        throw DragaliaError.WyrmprintAssemblingException(id, e)
    }

    override fun searchAdventurers(query: AdventurersQuery, limit: Int) =
        adventurersQueryMapper.toRemote(query)
            .asFlow()
            .map { dsQuery ->
                dsCache?.searchAdventurerIds(dsQuery, limit) ?: datasource.searchAdventurerIds(dsQuery, limit)
                    .also { dsCache?.cacheAdventurerCargoQuery(dsQuery, limit, it) }
            }
            .flattenConcatIterable()
            .map { it.asDragaliaId() }
            .map { getAdventurerById(it) }
            .filter { it in query }
            .catch { Napier.e(it.message ?: "", it) }

    override fun searchDragons(query: DragonsQuery, limit: Int) =
        dragonsQueryMapper.toRemote(query)
            .asFlow()
            .map { dsQuery ->
                dsCache?.searchDragonIds(dsQuery, limit) ?: datasource.searchDragonIds(dsQuery, limit).also {
                    dsCache?.cacheDragonCargoQuery(dsQuery, limit, it)
                }
            }
            .flattenConcatIterable()
            .map { asDragaliaId(it) }
            .map { getDragonById(it) }
            .filter { it in query }
            .catch { Napier.e(it.message ?: "", it) }

    override fun searchWyrmprints(query: WyrmprintsQuery, limit: Int) =
        wyrmprintsQueryMapper.toRemote(query)
            .asFlow()
            .map { dsQuery ->
                dsCache?.searchWyrmprintIds(dsQuery, limit) ?: datasource.searchWyrmprintIds(dsQuery, limit).also {
                    dsCache?.cacheWyrmprintCargoQuery(dsQuery, limit, it)
                }
            }
            .flattenMergeIterable()
            .map { asDragaliaId(it) }
            .map { getWyrmprintById(it) }
            .filter { it in query }
            .catch { Napier.e(it.message ?: "", it) }

    private fun CoroutineScope.getAbilityDataAsync(id: String) = async {
        getAndCacheAbilityById(id) withPair {
            getAndCacheAbilityIconImageInfoByFileName(AbilityIconName) to getAndCacheAbilityGroupByGroupId(AbilityGroup)
        }
    }

    private fun CoroutineScope.getCoAbilityDataAsync(id: String) = async {
        getAndCacheCoAbilityById(id).with { getAndCacheCoAbilityIconImageInfoByFileName(AbilityIconName) }
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

    private suspend fun getAndCacheAbilityById(abilityId: String) = dsCache?.getAbilityById(abilityId)
        ?: datasource.getAbilityById(abilityId).also { dsCache?.cacheAbilityById(abilityId, it) }

    private suspend fun getAndCacheSkillById(skillId: String) = dsCache?.getSkillById(skillId)
        ?: datasource.getSkillById(skillId).also { dsCache?.cacheSkillById(skillId, it) }

    private suspend fun getAndCacheSkillByName(skillId: String) = dsCache?.getSkillByName(skillId)
        ?: datasource.getSkillByName(skillId).also { dsCache?.cacheSkillByName(skillId, it) }

    private suspend fun getAndCacheCoAbilityById(coAbilityId: String) = dsCache?.getCoAbilityById(coAbilityId)
        ?: datasource.getCoAbilityById(coAbilityId).also { dsCache?.cacheCoAbilityById(coAbilityId, it) }

    private suspend fun getAndCacheAbilityIconImageInfoByFileName(fileName: String) =
        dsCache?.getAbilityIconByFileName(fileName) ?: datasource.getAbilityIconByFileName(fileName)
            ?.also { dsCache?.cacheAbilityIconByFileName(fileName, it) }

    private suspend fun getAndCacheCoAbilityIconImageInfoByFileName(fileName: String) =
        dsCache?.getCoAbilityIconByFileName(fileName) ?: datasource.getCoAbilityIconByFileName(fileName)
            ?.also { dsCache?.cacheCoAbilityIconByFileName(fileName, it) }

    private suspend fun getAndCacheSkillIconImageInfoByFileName(fileName: String) =
        dsCache?.getSkillIconByIconName(fileName) ?: datasource.getSkillIconByIconName(fileName)
            ?.also { dsCache?.cacheSkillIconByIconName(fileName, it) }

    private suspend fun getAndCacheAdventurerPortraitImageInfoByIds(id: String, variationId: String, rarity: Int) =
        rarity to (dsCache?.getAdventurerPortraitById(id, variationId, rarity)
            ?: datasource.getAdventurerPortraitById(id, variationId, rarity)
                ?.also { dsCache?.cacheAdventurerPortraitById(id, variationId, rarity, it) })

    private suspend fun getAndCacheAdventurerIconImageInfoByIds(id: String, variationId: String, rarity: Int) =
        rarity to (dsCache?.getAdventurerIconById(id, variationId, rarity)
            ?: datasource.getAdventurerIconById(id, variationId, rarity)
                ?.also { dsCache?.cacheAdventurerIconById(id, variationId, rarity, it) })

    private suspend fun getAndCacheDragonPortraitImageInfoById(id: String) =
        dsCache?.getDragonPortraitById(id) ?: datasource.getDragonPortraitById(id)
            ?.also { dsCache?.cacheDragonPortraitById(id, it) }

    private suspend fun getAndCacheDragonIconImageInfoById(id: String) =
        dsCache?.getDragonIconById(id) ?: datasource.getDragonIconById(id)
            ?.also { dsCache?.cacheDragonIconById(id, it) }

    private suspend fun getAndCacheWyrmprintIconImageInfoById(id: String, vestige: Int) =
        dsCache?.getWyrmprintIconByIds(id, vestige) ?: datasource.getWyrmprintIconByIds(id, vestige)
            ?.also { dsCache?.cacheWyrmprintIconByIds(id, vestige, it) }

    private suspend fun getAndCacheWyrmprintIconPortraitInfoById(id: String, vestige: Int) =
        dsCache?.getWyrmprintPortraitByIds(id, vestige) ?: datasource.getWyrmprintPortraitByIds(id, vestige)
            ?.also { dsCache?.cacheWyrmprintPortraitByIds(id, vestige, it) }

    private suspend fun getAndCacheAbilityGroupByGroupId(groupId: String) =
        dsCache?.getAbilityGroupsByGroupId(groupId) ?: datasource.getAbilityGroupsByGroupId(groupId)
            .also { dsCache?.cacheAbilityGroupsByGroupId(groupId, it) }

    private suspend fun GamepediaDatasourceCache.getAdventurerById(id: DragaliaId) =
        getAdventurerByIds(id.baseId, id.variationId!!)

    private suspend fun GamepediaDatasource.getAdventurerById(id: DragaliaId) =
        getAdventurerByIds(id.baseId, id.variationId!!)

    private suspend fun GamepediaDatasourceCache.cacheAdventurer(it: AdventurerJSON) =
        cacheAdventurerByIds(it.Id, it.VariationId, it)

    private suspend fun GamepediaDatasourceCache.getDragonById(id: DragaliaId) =
        getDragonById(id.baseId)

    private suspend fun GamepediaDatasource.getDragonById(id: DragaliaId) =
        getDragonById(id.baseId)

    private suspend fun GamepediaDatasourceCache.cacheDragon(it: DragonJSON) =
        cacheDragonById(it.Id, it)

    private suspend fun GamepediaDatasourceCache.getWyrmprintById(id: DragaliaId) =
        getWyrmprintById(id.baseId)

    private suspend fun GamepediaDatasource.getWyrmprintById(id: DragaliaId) =
        getWyrmprintById(id.baseId)

    private suspend fun GamepediaDatasourceCache.cacheWyrmprint(it: WyrmprintJSON) =
        cacheWyrmprintById(it.Id, it)

    private suspend fun AdventurerJSON.asEntity() = coroutineScope {
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

        adventurerMapper(
            AdventurerMapper.Params(
                this@asEntity, a11.await(), a12.await(), a13?.await(), a21.await(), a22.await(), a23?.await(),
                a31.await(), a32?.await(), a33?.await(), coa1.await(), coa2.await(), coa3.await(),
                coa4.await(), coa5.await(), s1.await(), s2.await(), images.awaitAll(),
                icons.awaitAll()
            )
        )
    }

    private suspend fun DragonJSON.asEntity() = coroutineScope {
        val a11 = getAbilityDataAsync(Abilities11)
        val a12 = getAbilityDataAsync(Abilities12)

        val a21 = Abilities21.ifIsNotBlankOrZero { getAbilityDataAsync(it) }
        val a22 = Abilities22.ifIsNotBlankOrZero { getAbilityDataAsync(it) }

        val s1 = getSkillDataByNameAsync(SkillName)

        val icon = async { getAndCacheDragonIconImageInfoById(BaseId) }
        val portrait = async { getAndCacheDragonPortraitImageInfoById(BaseId) }

        dragonMapper(
            DragonMapper.Params(
                this@asEntity, a11.await(), a12.await(), a21?.await(), a22?.await(),
                s1.await(), icon.await(), portrait.await()
            )
        )
    }

    private suspend fun WyrmprintJSON.asEntity() = coroutineScope {
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

        wyrmprintMapper(
            WyrmprintMapper.Params(
                this@asEntity, a11.await(), a12.await(), a13.await(), a21?.await(), a22?.await(),
                a23?.await(), a31?.await(), a32?.await(), a33?.await(), icon1.await(),
                icon2.await(), artwork1.await(), artwork2.await()
            )
        )
    }

    private suspend fun getAndCacheAdventurerJson(id: DragaliaId) =
        (dsCache?.getAdventurerById(id) ?: datasource.getAdventurerById(id).also { dsCache?.cacheAdventurer(it) })
            .asEntity()

    private suspend fun getAndCacheDragonJson(id: DragaliaId) =
        (dsCache?.getDragonById(id) ?: datasource.getDragonById(id).also { dsCache?.cacheDragon(it) })
            .asEntity()

    private suspend fun getAndCacheWyrmprintJson(id: DragaliaId) =
        (dsCache?.getWyrmprintById(id) ?: datasource.getWyrmprintById(id).also { dsCache?.cacheWyrmprint(it) })
            .asEntity()

}
