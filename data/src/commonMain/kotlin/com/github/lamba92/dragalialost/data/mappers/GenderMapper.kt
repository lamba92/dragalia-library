package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.domain.entities.enums.Gender

class GenderMapper : SingleFromRemoteMapper<String, Gender> {

    override fun fromRemoteSingle(remote: String) =
        Gender.values().first { it.name.toLowerCase() == remote.toLowerCase() }

}
