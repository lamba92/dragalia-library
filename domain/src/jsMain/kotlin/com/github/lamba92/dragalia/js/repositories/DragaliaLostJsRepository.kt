package com.github.lamba92.dragalia.js.repositories

import com.github.lamba92.dragalia.entities.AdventurerEntity
import com.github.lamba92.dragalia.entities.DragonEntity
import com.github.lamba92.dragalia.entities.WyrmprintEntity
import com.github.lamba92.dragalia.repositories.queries.AdventurersQueryBuilder
import com.github.lamba92.dragalia.repositories.queries.DragonsQueryBuilder
import com.github.lamba92.dragalia.repositories.queries.WyrmprintsQueryBuilder
import kotlin.js.Promise

interface DragaliaLostJsRepository {

    fun searchAdventurers(
        query: AdventurersQueryBuilder
    ): Promise<List<AdventurerEntity>>

    fun searchDragons(
        query: DragonsQueryBuilder
    ): Promise<List<DragonEntity>>

    fun searchWyrmprints(
        query: WyrmprintsQueryBuilder
    ): Promise<List<WyrmprintEntity>>

}