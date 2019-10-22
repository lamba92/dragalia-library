package com.github.lamba92.dragalialost.domain.repositories.ext

import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQueryBuilder

suspend fun DragaliaLostRepository.searchAdventurers(
    limit: Int = 500,
    queryBuilder: AdventurersQueryBuilder.() -> Unit
) =
    searchAdventurers(AdventurersQueryBuilder().apply(queryBuilder), limit)

suspend fun DragaliaLostRepository.searchDragons(limit: Int = 500, queryBuilder: DragonsQueryBuilder.() -> Unit) =
    searchDragons(DragonsQueryBuilder().apply(queryBuilder), limit)

suspend fun DragaliaLostRepository.searchWyrmprints(limit: Int = 500, queryBuilder: WyrmprintsQueryBuilder.() -> Unit) =
    searchWyrmprints(WyrmprintsQueryBuilder().apply(queryBuilder), limit)