package com.github.lamba92.rawresponses

import kotlinx.serialization.Serializable

@Serializable
data class NestedTitleJSON<T>(val title: T)