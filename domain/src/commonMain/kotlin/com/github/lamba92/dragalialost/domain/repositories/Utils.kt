@file:Suppress("unused")

package com.github.lamba92.dragalialost.domain.repositories

import com.github.lamba92.dragalialost.domain.repositories.queries.builders.AdventurersQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.builders.DragonsQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.builders.WyrmprintsQueryBuilder

suspend fun DragaliaLostRepository.searchAdventurers(
    limit: Int = 500,
    queryBuilder: AdventurersQueryBuilder.() -> Unit
) =
    searchAdventurers(AdventurersQueryBuilder().apply(queryBuilder).build(), limit)

suspend fun DragaliaLostRepository.searchDragons(
    limit: Int = 500, queryBuilder: DragonsQueryBuilder.() -> Unit
) =
    searchDragons(DragonsQueryBuilder().apply(queryBuilder).build(), limit)

suspend fun DragaliaLostRepository.searchWyrmprints(
    limit: Int = 500,
    queryBuilder: WyrmprintsQueryBuilder.() -> Unit
) =
    searchWyrmprints(WyrmprintsQueryBuilder().apply(queryBuilder).build(), limit)

suspend fun DragaliaLostRepository.searchAllAdventurers() =
    searchAdventurers {}

suspend fun DragaliaLostRepository.searchAllDragons() =
    searchDragons {}

suspend fun DragaliaLostRepository.searchAllWyrmprints() =
    searchWyrmprints {}
