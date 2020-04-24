package com.github.lamba92.dragalialost.domain.repositories

import com.github.lamba92.dragalialost.domain.entities.AdventurerEntity
import com.github.lamba92.dragalialost.domain.entities.DragaliaId
import com.github.lamba92.dragalialost.domain.entities.DragonEntity
import com.github.lamba92.dragalialost.domain.entities.WyrmprintEntity
import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQuery
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQuery
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQuery

interface DragaliaLostRepository {

    suspend fun searchAdventurers(
        query: AdventurersQuery,
        limit: Int = 500
    ): List<AdventurerEntity>

    suspend fun searchDragons(
        query: DragonsQuery,
        limit: Int = 500
    ): List<DragonEntity>

    suspend fun searchWyrmprints(
        query: WyrmprintsQuery,
        limit: Int = 500
    ): List<WyrmprintEntity>

    suspend fun getAdventurerById(id: DragaliaId): AdventurerEntity
    suspend fun getDragonById(id: DragaliaId): DragonEntity
    suspend fun getWyrmprintById(id: DragaliaId): WyrmprintEntity

}
