package com.github.lamba92.dragalialost.domain.js.repositories

import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQueryBuilder

fun DragaliaLostJsRepository.searchAdventurers(queryBuilder: AdventurersQueryBuilder.() -> Unit) =
    searchAdventurers(AdventurersQueryBuilder().apply(queryBuilder).toQuery())

fun DragaliaLostJsRepository.searchDragons(queryBuilder: DragonsQueryBuilder.() -> Unit) =
    searchDragons(DragonsQueryBuilder().apply(queryBuilder).toQuery())

fun DragaliaLostJsRepository.searchWyrmprints(queryBuilder: WyrmprintsQueryBuilder.() -> Unit) =
    searchWyrmprints(WyrmprintsQueryBuilder().apply(queryBuilder).toQuery())

fun DragaliaLostJsRepository.searchAllAdventurers() =
    searchAdventurers {}

fun DragaliaLostJsRepository.searchAllDragons() =
    searchDragons {}

fun DragaliaLostJsRepository.searchAllWyrmprints() =
    searchWyrmprints {}