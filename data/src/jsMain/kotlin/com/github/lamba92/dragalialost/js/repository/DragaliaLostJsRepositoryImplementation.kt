package com.github.lamba92.dragalialost.js.repository

import com.github.lamba92.dragalia.js.repositories.DragaliaLostJsRepository
import com.github.lamba92.dragalia.repositories.DragaliaLostRepository
import com.github.lamba92.dragalia.repositories.queries.AdventurersQueryBuilder
import com.github.lamba92.dragalia.repositories.queries.DragonsQueryBuilder
import com.github.lamba92.dragalia.repositories.queries.WyrmprintsQueryBuilder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise

class DragaliaLostJsRepositoryImplementation(
    private val dragaliaLostRepository: DragaliaLostRepository
) : DragaliaLostJsRepository {

    override fun searchAdventurers(query: AdventurersQueryBuilder) =
        GlobalScope.promise { dragaliaLostRepository.searchAdventurers(query) }

    override fun searchDragons(query: DragonsQueryBuilder) =
        GlobalScope.promise { dragaliaLostRepository.searchDragons(query) }

    override fun searchWyrmprints(query: WyrmprintsQueryBuilder) =
        GlobalScope.promise { dragaliaLostRepository.searchWyrmprints(query) }

}