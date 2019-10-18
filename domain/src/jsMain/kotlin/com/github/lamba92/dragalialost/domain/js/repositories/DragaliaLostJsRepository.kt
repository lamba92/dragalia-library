package com.github.lamba92.dragalialost.domain.js.repositories

import com.github.lamba92.dragalialost.domain.entities.AdventurerEntity
import com.github.lamba92.dragalialost.domain.entities.DragonEntity
import com.github.lamba92.dragalialost.domain.entities.WyrmprintEntity
import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQueryBuilder
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQueryBuilder
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