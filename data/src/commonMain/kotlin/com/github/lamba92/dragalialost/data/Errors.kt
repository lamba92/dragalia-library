package com.github.lamba92.dragalialost.data

import com.github.lamba92.dragalialost.domain.entities.DragaliaId

sealed class DragaliaError : Throwable() {

    abstract override val cause: Throwable
    abstract override val message: String
    abstract val id: DragaliaId

    data class AbilityNotFoundException(
        override val id: DragaliaId,
        override val cause: Throwable,
        override val message: String = "Unable to find ability on Gamepedia with id ${id.baseId}"
    ) : DragaliaError() {
//        companion object {
//            operator fun invoke(
//                id: String,
//                cause: Throwable,
//                message: String = "Unable to find ability on Gamepedia with id $id"
//            ): DragaliaError.AbilityNotFoundException = AbilityNotFoundException(asDragaliaId(id), cause, message)
//        }
    }

    data class AdventurerAssemblingException(
        override val id: DragaliaId,
        override val cause: Throwable,
        override val message: String = "Unable to assemble adventurer with id $id due to $cause"
    ) : DragaliaError()

    data class DragonAssemblingException(
        override val id: DragaliaId,
        override val cause: Throwable,
        override val message: String = "Unable to assemble dragon with id $id due to $cause"
    ) : DragaliaError()

    data class WyrmprintAssemblingException(
        override val id: DragaliaId,
        override val cause: Throwable,
        override val message: String = "Unable to assemble wyrmprint with id $id due to $cause"
    ) : DragaliaError()
}
