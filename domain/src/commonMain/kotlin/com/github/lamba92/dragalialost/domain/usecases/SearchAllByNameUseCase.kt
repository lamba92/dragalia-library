package com.github.lamba92.dragalialost.domain.usecases

import com.github.lamba92.dragalialost.domain.entities.DragaliaEntity
import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.domain.repositories.searchAdventurers
import com.github.lamba92.dragalialost.domain.repositories.searchDragons
import com.github.lamba92.dragalialost.domain.repositories.searchWyrmprints
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchAllByNameUseCase(
    private val repo: DragaliaLostRepository
) : UseCaseWithParams<String, List<DragaliaEntity>> {

    @OptIn(FlowPreview::class)
    override suspend fun buildAction(params: String) =
        flowOf(
            repo.searchAdventurers { name = params },
            repo.searchDragons { name = params },
            repo.searchWyrmprints { name = params }
        ).flattenMerge(3).toList().sortedBy { it.name }

}
