package com.github.lamba92.dragalialost.domain.entities.support

import kotlinx.serialization.Serializable

@Serializable
data class WyrmprintDescription(
    val stage1: String,
    val stage2: String,
    val stage3: String,
    val stage4: String,
    val stage5: String
)
