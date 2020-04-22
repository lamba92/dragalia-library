package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.domain.entities.enums.Race
import com.github.lamba92.dragalialost.domain.entities.enums.Race.NOT_AVAILABLE

class RaceMapper : SingleFromRemoteMapper<String, Race> {
    override fun fromRemoteSingle(remote: String) =
        try {
            Race.valueOf(remote.toUpperCase())
        } catch (e: IllegalArgumentException) {
            NOT_AVAILABLE
        }
}
