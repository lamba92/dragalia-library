package com.github.lamba92.dragalialost.domain.usecases

import com.github.lamba92.dragalialost.domain.entities.DragaliaEntity
import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.domain.repositories.searchAdventurers
import com.github.lamba92.dragalialost.domain.repositories.searchDragons
import com.github.lamba92.dragalialost.domain.repositories.searchWyrmprints
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOf

class SearchAllByNameUseCase(
    private val repo: DragaliaLostRepository
) : UseCaseWithParams<String, Flow<DragaliaEntity>> {
    @FlowPreview
    override fun buildAction(params: String) =
        flowOf(
            repo.searchAdventurers { name = params },
            repo.searchDragons { name = params },
            repo.searchWyrmprints { name = params }
        ).flattenMerge()

}