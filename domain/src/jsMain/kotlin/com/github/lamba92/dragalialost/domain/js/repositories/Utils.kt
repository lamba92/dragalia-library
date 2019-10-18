package com.github.lamba92.dragalialost.domain.js.repositories

import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQueryBuilder

fun DragaliaLostJsRepository.searchAdventurers(queryBuilder: AdventurersQueryBuilder.() -> Unit) =
    searchAdventurers(AdventurersQueryBuilder().apply(queryBuilder))

fun DragaliaLostJsRepository.searchDragons(queryBuilder: DragonsQueryBuilder.() -> Unit) =
    searchDragons(DragonsQueryBuilder().apply(queryBuilder))

fun DragaliaLostJsRepository.searchWyrmprints(queryBuilder: WyrmprintsQueryBuilder.() -> Unit) =
    searchWyrmprints(WyrmprintsQueryBuilder().apply(queryBuilder))