package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.domain.entities.enums.Gender

class GenderMapper : SingleFromRemoteMapper<String, Gender> {

    override fun fromRemoteSingle(remote: String) =
        try {
            Gender.valueOf(remote.toUpperCase())
        } catch (e: IllegalArgumentException) {
            Gender.OTHER
        }

}
