package com.github.lamba92.rawresponses

import kotlinx.serialization.Serializable

@Serializable
data class CargoJSON<T>(val cargoquery: List<NestedTitleJSON<T>>)