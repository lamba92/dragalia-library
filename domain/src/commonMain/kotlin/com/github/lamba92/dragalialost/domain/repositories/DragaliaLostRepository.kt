package com.github.lamba92.dragalialost.domain.repositories

import com.github.lamba92.dragalialost.domain.entities.AdventurerEntity
import com.github.lamba92.dragalialost.domain.entities.DragonEntity
import com.github.lamba92.dragalialost.domain.entities.WyrmprintEntity
import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQuery
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQuery
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQuery
import kotlinx.coroutines.flow.Flow

interface DragaliaLostRepository {

    suspend fun searchAdventurers(
        query: AdventurersQuery,
        limit: Int = 500
    ): Flow<AdventurerEntity>

    suspend fun searchDragons(
        query: DragonsQuery,
        limit: Int = 500
    ): Flow<DragonEntity>

    suspend fun searchWyrmprints(
        query: WyrmprintsQuery,
        limit: Int = 500
    ): Flow<WyrmprintEntity>

}