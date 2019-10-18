package com.github.lamba92.dragalialost.domain.repositories.ext

import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQueryBuilder

suspend fun DragaliaLostRepository.searchAdventurers(queryBuilder: AdventurersQueryBuilder.() -> Unit) =
    searchAdventurers(AdventurersQueryBuilder().apply(queryBuilder))

suspend fun DragaliaLostRepository.searchDragons(queryBuilder: DragonsQueryBuilder.() -> Unit) =
    searchDragons(DragonsQueryBuilder().apply(queryBuilder))

suspend fun DragaliaLostRepository.searchWyrmprints(queryBuilder: WyrmprintsQueryBuilder.() -> Unit) =
    searchWyrmprints(WyrmprintsQueryBuilder().apply(queryBuilder))