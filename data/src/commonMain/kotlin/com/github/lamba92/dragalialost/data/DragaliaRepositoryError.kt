package com.github.lamba92.dragalialost.data

import com.github.lamba92.dragalialost.domain.entities.DragaliaId

sealed class DragaliaRepositoryError : DragaliaError() {

    abstract val id: DragaliaId

    class AdventurerAssemblingException(
        override val id: DragaliaId,
        override val cause: Throwable,
        override val message: String = "Unable to assemble adventurer with id $id"
    ) : DragaliaRepositoryError()

    class DragonAssemblingException(
        override val id: DragaliaId,
        override val cause: Throwable,
        override val message: String = "Unable to assemble dragon with id $id"
    ) : DragaliaRepositoryError()

    class WyrmprintAssemblingException(
        override val id: DragaliaId,
        override val cause: Throwable,
        override val message: String = "Unable to assemble wyrmprint with id $id"
    ) : DragaliaRepositoryError()
}
