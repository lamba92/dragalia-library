package com.github.lamba92.dragalialost.data.repositories

import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasource
import com.github.lamba92.dragalialost.data.mappers.*
import com.github.lamba92.dragalialost.data.utils.deferredMap
import com.github.lamba92.dragalialost.data.utils.flatMapIterableConcat
import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQueryBuilder
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList

class DragaliaLostRepositoryImplementation(
    private val gamepediaDatasource: GamepediaDatasource,
    private val adventurersQueryMapper: AdventurersQueryMapper,
    private val wyrmprintsQueryMapper: WyrmprintsQueryMapper,
    private val dragonsQueryMapper: DragonsQueryMapper,
    private val dragonsMapper: DragonsMapper,
    private val adventurersMapper: AdventurersMapper,
    private val wyrmprintsMapper: WyrmprintsMapper
) : DragaliaLostRepository {

    @FlowPreview
    override suspend fun searchAdventurers(query: AdventurersQueryBuilder) =
        adventurersQueryMapper.toRemote(query)
            .asFlow()
            .deferredMap { gamepediaDatasource.searchAdventurers(it) }
            .flatMapIterableConcat { adventurersMapper.fromRemoteMultiple(it) }
            .toList()

    @FlowPreview
    override suspend fun searchDragons(query: DragonsQueryBuilder) =
        dragonsQueryMapper.toRemote(query)
            .asFlow()
            .deferredMap { gamepediaDatasource.searchDragons(it) }
            .flatMapIterableConcat { dragonsMapper.fromRemoteMultiple(it) }
            .toList()

    @FlowPreview
    override suspend fun searchWyrmprints(query: WyrmprintsQueryBuilder) =
        wyrmprintsQueryMapper.toRemote(query)
            .asFlow()
            .deferredMap { gamepediaDatasource.searchWyrmprints(it) }
            .flatMapIterableConcat { wyrmprintsMapper.fromRemoteMultiple(it) }
            .toList()

}