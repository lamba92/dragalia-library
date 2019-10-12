package com.github.lamba92.rawresponses

import com.github.lamba92.entities.DragaliaEntity
import kotlinx.serialization.Serializable

@Serializable
data class CargoJSON<T : DragaliaEntity>(val cargoquery: List<NestedTitleJSON<T>>)