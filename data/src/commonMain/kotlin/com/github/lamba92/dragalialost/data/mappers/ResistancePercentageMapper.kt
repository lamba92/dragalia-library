package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.domain.entities.enums.ResistancePercentage

class ResistancePercentageMapper : SingleFromRemoteMapper<String, ResistancePercentage> {
    override fun fromRemoteSingle(remote: String) = when (remote) {
        "50" -> ResistancePercentage.FIFTY
        "100" -> ResistancePercentage.ONE_HUNDRED
        else -> ResistancePercentage.ZERO
    }
}
