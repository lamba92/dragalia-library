package com.github.lamba92.dragalialost.domain.usecases

import com.github.lamba92.dragalialost.domain.entities.DragaliaEntity
import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.domain.repositories.searchAdventurers
import com.github.lamba92.dragalialost.domain.repositories.searchDragons
import com.github.lamba92.dragalialost.domain.repositories.searchWyrmprints
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class SearchAllByNameUseCase(
    private val repo: DragaliaLostRepository
) : UseCaseWithParams<String, List<DragaliaEntity>> {

    override suspend fun buildAction(params: String) = coroutineScope {
        val a = async { repo.searchAdventurers { name = params } }
        val d = async { repo.searchDragons { name = params } }
        val w = async { repo.searchWyrmprints { name = params } }
        (a.await() + d.await() + w.await()).sortedBy { it.name }
    }

}
