package com.github.lamba92.dragalia.repositories

import com.github.lamba92.dragalia.repositories.queries.AdventurersQueryBuilder
import com.github.lamba92.dragalia.repositories.queries.DragonsQueryBuilder
import com.github.lamba92.dragalia.repositories.queries.WyrmprintsQueryBuilder

suspend fun DragaliaLostRepository.searchAdventurers(queryBuilder: AdventurersQueryBuilder.() -> Unit) =
    searchAdventurers(AdventurersQueryBuilder().apply(queryBuilder))

suspend fun DragaliaLostRepository.searchDragons(queryBuilder: DragonsQueryBuilder.() -> Unit) =
    searchDragons(DragonsQueryBuilder().apply(queryBuilder))

suspend fun DragaliaLostRepository.searchWyrmprints(queryBuilder: WyrmprintsQueryBuilder.() -> Unit) =
    searchWyrmprints(WyrmprintsQueryBuilder().apply(queryBuilder))