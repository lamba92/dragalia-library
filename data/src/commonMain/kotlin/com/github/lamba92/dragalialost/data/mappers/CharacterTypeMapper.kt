package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.domain.entities.enums.HeroCLass

class CharacterTypeMapper : SingleFromRemoteMapper<String, HeroCLass> {
    override fun fromRemoteSingle(remote: String) =
        HeroCLass.values().first { it.name.capitalize() == remote }

}
