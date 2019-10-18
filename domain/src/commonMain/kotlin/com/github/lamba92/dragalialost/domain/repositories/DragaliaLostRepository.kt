package com.github.lamba92.dragalialost.domain.repositories

import com.github.lamba92.dragalialost.domain.entities.AdventurerEntity
import com.github.lamba92.dragalialost.domain.entities.DragonEntity
import com.github.lamba92.dragalialost.domain.entities.WyrmprintEntity
import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQueryBuilder

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