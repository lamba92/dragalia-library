package com.github.lamba92.dragalialost.domain.repositories

import com.github.lamba92.dragalialost.domain.entities.AdventurerEntity
import com.github.lamba92.dragalialost.domain.entities.DragonEntity
import com.github.lamba92.dragalialost.domain.entities.WyrmprintEntity
import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQueryBuilder
import kotlinx.coroutines.flow.Flow

interface DragaliaLostRepository {

    suspend fun searchAdventurers(
        query: AdventurersQueryBuilder,
        limit: Int = 500
    ): Flow<AdventurerEntity>

    suspend fun searchDragons(
        query: DragonsQueryBuilder,
        limit: Int = 500
    ): Flow<DragonEntity>

    suspend fun searchWyrmprints(
        query: WyrmprintsQueryBuilder,
        limit: Int = 500
    ): Flow<WyrmprintEntity>

}