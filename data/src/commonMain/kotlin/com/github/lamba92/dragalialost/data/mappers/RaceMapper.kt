package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.domain.entities.enums.Race

class RaceMapper : SingleFromRemoteMapper<String, Race> {
    override fun fromRemoteSingle(remote: String) =
        Race.values().first { it.name.capitalize() == remote }
}
