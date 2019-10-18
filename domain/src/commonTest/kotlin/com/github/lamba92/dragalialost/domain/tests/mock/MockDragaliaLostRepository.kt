package com.github.lamba92.dragalialost.domain.tests.mock

import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQueryBuilder

class MockDragaliaLostRepository : DragaliaLostRepository {

    override suspend fun searchAdventurers(query: AdventurersQueryBuilder) =
        listOf(MockEntities.adventurer)

    override suspend fun searchDragons(query: DragonsQueryBuilder) =
        listOf(MockEntities.dragon)

    override suspend fun searchWyrmprints(query: WyrmprintsQueryBuilder) =
        listOf(MockEntities.wyrmprint)

}