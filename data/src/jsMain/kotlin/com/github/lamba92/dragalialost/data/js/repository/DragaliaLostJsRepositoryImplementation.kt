package com.github.lamba92.dragalialost.data.js.repository

import com.github.lamba92.dragalialost.domain.js.repositories.DragaliaLostJsRepository
import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.domain.repositories.queries.AdventurersQuery
import com.github.lamba92.dragalialost.domain.repositories.queries.DragonsQuery
import com.github.lamba92.dragalialost.domain.repositories.queries.WyrmprintsQuery
import kotlinx.coroutines.ExperimentalCoroutinesApi

class DragaliaLostJsRepositoryImplementation(
    private val dragaliaLostRepository: DragaliaLostRepository
) : DragaliaLostJsRepository {

    @ExperimentalCoroutinesApi
    override fun searchAdventurers(query: AdventurersQuery) =
        dragaliaLostRepository.searchAdventurers(query).toObservable()

    @ExperimentalCoroutinesApi
    override fun searchDragons(query: DragonsQuery) =
        dragaliaLostRepository.searchDragons(query).toObservable()

    @ExperimentalCoroutinesApi
    override fun searchWyrmprints(query: WyrmprintsQuery) =
        dragaliaLostRepository.searchWyrmprints(query).toObservable()

}