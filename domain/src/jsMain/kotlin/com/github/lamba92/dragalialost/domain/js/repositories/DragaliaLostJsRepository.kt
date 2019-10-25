package com.github.lamba92.dragalialost.domain.js.repositories

import com.github.lamba92.dragalialost.domain.entities.AdventurerEntity
import com.github.lamba92.dragalialost.domain.entities.DragonEntity
import com.github.lamba92.dragalialost.domain.entities.WyrmprintEntity
import com.github.lamba92.dragalialost.domain.js.externals.Observable
import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQuery
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQuery
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQuery


interface DragaliaLostJsRepository {

    fun searchAdventurers(
        query: AdventurersQuery
    ): Observable<AdventurerEntity>

    fun searchDragons(
        query: DragonsQuery
    ): Observable<DragonEntity>

    fun searchWyrmprints(
        query: WyrmprintsQuery
    ): Observable<WyrmprintEntity>

}