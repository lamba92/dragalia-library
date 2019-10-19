package com.github.lamba92.dragalialost.data.repositories

import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasource
import com.github.lamba92.dragalialost.data.mappers.*
import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQueryBuilder
import kotlinx.coroutines.FlowPreview

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
        TODO()

    @FlowPreview
    override suspend fun searchDragons(query: DragonsQueryBuilder) =
        TODO()

    @FlowPreview
    override suspend fun searchWyrmprints(query: WyrmprintsQueryBuilder) =
        TODO()

}