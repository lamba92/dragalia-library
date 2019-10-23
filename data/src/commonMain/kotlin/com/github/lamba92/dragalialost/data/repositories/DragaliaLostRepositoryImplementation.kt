package com.github.lamba92.dragalialost.data.repositories

import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasource
import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasourceCache
import com.github.lamba92.dragalialost.data.mappers.*
import com.github.lamba92.dragalialost.data.utils.flattenConcat
import com.github.lamba92.dragalialost.data.utils.ifIsNotBlankOrZero
import com.github.lamba92.dragalialost.data.utils.scopedMap
import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQueryBuilder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toSet

class DragaliaLostRepositoryImplementation(
    private val datasource: GamepediaDatasource,
    private val cache: GamepediaDatasourceCache,
    private val adventurersQueryMapper: AdventurersQueryMapper,
    private val wyrmprintsQueryMapper: WyrmprintsQueryMapper,
    private val dragonsQueryMapper: DragonsQueryMapper,
    private val dragonsMapper: DragonsMapper,
    private val adventurerMapper: AdventurerMapper,
    private val wyrmprintsMapper: WyrmprintsMapper
) : DragaliaLostRepository {

    @ExperimentalCoroutinesApi
    @FlowPreview
    override suspend fun searchAdventurers(query: AdventurersQueryBuilder, limit: Int) =
        adventurersQueryMapper.toRemote(query)
            .asFlow()
            .map { dsQuery ->
                cache.searchAdventurerIds(dsQuery, limit) ?: datasource.searchAdventurerIds(dsQuery, limit).also {
                    cache.cacheAdventurerCargoQuery(dsQuery, limit, it)
                }
            }
            .flattenConcat()
            .toSet()
            .asFlow()
            .map { (id, variationId) ->
                cache.getAdventurerByIds(id, variationId) ?: datasource.getAdventurerByIds(id, variationId)
                    .also { cache.cacheAdventurerByIds(id, variationId, it) }
            }
            .scopedMap { json ->
                with(json) {
                    val a11 = async { getAndCacheAbilityById(Abilities11) }
                    val a12 = async { getAndCacheAbilityById(Abilities12) }
                    val a13 = Abilities13.ifIsNotBlankOrZero { async { getAndCacheAbilityById(it) } }

                    val a21 = async { getAndCacheAbilityById(Abilities21) }
                    val a22 = async { getAndCacheAbilityById(Abilities22) }
                    val a23 = Abilities23.ifIsNotBlankOrZero { async { getAndCacheAbilityById(it) } }

                    val a31 = async { getAndCacheAbilityById(Abilities31) }
                    val a32 = Abilities32.ifIsNotBlankOrZero { async { getAndCacheAbilityById(it) } }
                    val a33 = Abilities33.ifIsNotBlankOrZero { async { getAndCacheAbilityById(it) } }

                    val s1 = async { getAndCacheSkillByName(Skill1Name) }
                    val s2 = async { getAndCacheSkillByName(Skill2Name) }

                    val coa1 = async { getAndCacheCoAbilityById(ExAbilityData1) }
                    val coa2 = async { getAndCacheCoAbilityById(ExAbilityData2) }
                    val coa3 = async { getAndCacheCoAbilityById(ExAbilityData3) }
                    val coa4 = async { getAndCacheCoAbilityById(ExAbilityData4) }
                    val coa5 = async { getAndCacheCoAbilityById(ExAbilityData5) }

                    val images = (Rarity.toInt()..5).map {
                        async { datasource.getAdventurerPortraitById(Id, VariationId, it) }
                    }
                    val icons = (Rarity.toInt()..5).map {
                        async { datasource.getAdventurerIconById(Id, VariationId, it) }
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
            .catch {
                println("An adventurer errored: $it")
            }

    @ExperimentalCoroutinesApi
    @FlowPreview
    override suspend fun searchDragons(query: DragonsQueryBuilder, limit: Int) =
        dragonsQueryMapper.toRemote(query)
            .asFlow()
            .map { dsQuery ->
                cache.searchDragonIds(dsQuery, limit) ?: datasource.searchDragonIds(dsQuery, limit).also {
                    cache.cacheDragonCargoQuery(dsQuery, limit, it)
                }
            }
            .flattenConcat()
            .toSet()
            .asFlow()
            .map { dragonId ->
                cache.getDragonById(dragonId) ?: datasource.getDragonById(dragonId)
                    .also { cache.cacheDragonById(dragonId, it) }
            }
            .scopedMap { json ->
                with(json) {
                    val a11 = async { getAndCacheAbilityById(Abilities11) }
                    val a12 = async { getAndCacheAbilityById(Abilities12) }

                    val a21 = Abilities21.ifIsNotBlankOrZero { async { getAndCacheAbilityById(it) } }
                    val a22 = Abilities22.ifIsNotBlankOrZero { async { getAndCacheAbilityById(it) } }

                    val s1 = async { getAndCacheSkillByName(SkillName) }

                    val icon = async { datasource.getDragonIconByIdUrl(BaseId) }
                    val portrait = async { datasource.getDragonPortraitById(BaseId) }

                    DragonsMapper.Params(
                        json, a11.await(), a12.await(), a21?.await(), a22?.await(),
                        s1.await(), icon.await(), portrait.await()
                    )
                }
            }
            .catch {
                println("A dragon errored: $it")
            }
            .map { dragonsMapper(it) }

    @ExperimentalCoroutinesApi
    @FlowPreview
    override suspend fun searchWyrmprints(query: WyrmprintsQueryBuilder, limit: Int) =
        wyrmprintsQueryMapper.toRemote(query)
            .asFlow()
            .map { dsQuery ->
                cache.searchWyrmprintIds(dsQuery, limit) ?: datasource.searchWyrmprintIds(dsQuery, limit).also {
                    cache.cacheWyrmprintCargoQuery(dsQuery, limit, it)
                }
            }
            .flattenConcat()
            .toSet()
            .asFlow()
            .map { id ->
                cache.getWyrmprintById(id) ?: datasource.getWyrmprintById(id)
                    .also { cache.cacheWyrmprintById(id, it) }
            }
            .scopedMap { json ->
                with(json) {
                    val a11 = async { getAndCacheAbilityById(Abilities11) }
                    val a12 = async { getAndCacheAbilityById(Abilities12) }
                    val a13 = async { getAndCacheAbilityById(Abilities13) }

                    val a21 = Abilities21.ifIsNotBlankOrZero { async { getAndCacheAbilityById(it) } }
                    val a22 = Abilities22.ifIsNotBlankOrZero { async { getAndCacheAbilityById(it) } }
                    val a23 = Abilities23.ifIsNotBlankOrZero { async { getAndCacheAbilityById(it) } }

                    val a31 = Abilities31.ifIsNotBlankOrZero { async { getAndCacheAbilityById(it) } }
                    val a32 = Abilities32.ifIsNotBlankOrZero { async { getAndCacheAbilityById(it) } }
                    val a33 = Abilities33.ifIsNotBlankOrZero { async { getAndCacheAbilityById(it) } }

                    val icon1 = async { datasource.getWyrmprintIconByIds(Id, 1) }
                    val icon2 = async { datasource.getWyrmprintIconByIds(Id, 2) }
                    val artwork1 = async { datasource.getWyrmprintPortraitByIds(Id, 1) }
                    val artwork2 = async { datasource.getWyrmprintPortraitByIds(Id, 2) }

                    WyrmprintsMapper.Params(
                        json, a11.await(), a12.await(), a13.await(), a21?.await(), a22?.await(),
                        a23?.await(), a31?.await(), a32?.await(), a33?.await(), icon1.await(),
                        icon2.await(), artwork1.await(), artwork2.await()
                    )
                }
            }
            .catch {
                println("A wyrmprint errored: $it")
            }
            .map { wyrmprintsMapper(it) }

    private suspend fun getAndCacheAbilityById(abilityId: String) = cache.getAbilityById(abilityId)
        ?: datasource.getAbilityById(abilityId).also { cache.cacheAbilityById(abilityId, it) }

    private suspend fun getAndCacheSkillByName(skillId: String) = cache.getSkillByName(skillId)
        ?: datasource.getSkillByName(skillId).also { cache.cacheSkillByName(skillId, it) }

    private suspend fun getAndCacheCoAbilityById(coAbilityId: String) = cache.getCoAbilityById(coAbilityId)
        ?: datasource.getCoAbilityById(coAbilityId).also { cache.cacheCoAbilityById(coAbilityId, it) }

}
