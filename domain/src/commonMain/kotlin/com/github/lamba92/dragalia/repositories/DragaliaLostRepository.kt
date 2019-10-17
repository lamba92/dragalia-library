package com.github.lamba92.dragalia.repositories

import com.github.lamba92.dragalia.entities.AdventurerEntity
import com.github.lamba92.dragalia.entities.DragonEntity
import com.github.lamba92.dragalia.entities.WyrmprintEntity
import com.github.lamba92.dragalia.repositories.queries.AdventurersQueryBuilder
import com.github.lamba92.dragalia.repositories.queries.DragonsQueryBuilder
import com.github.lamba92.dragalia.repositories.queries.WyrmprintsQueryBuilder

interface DragaliaLostRepository {

    suspend fun searchAdventurers(
        query: AdventurersQueryBuilder
    ): List<AdventurerEntity>

    suspend fun searchDragons(
        query: DragonsQueryBuilder
    ): List<DragonEntity>

    suspend fun searchWyrmprints(
        query: WyrmprintsQueryBuilder
    ): List<WyrmprintEntity>

}