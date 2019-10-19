package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.domain.entities.enums.HeroCLass

class HeroClassMapper : SingleToRemoteMapper<String, HeroCLass>, SingleFromRemoteMapper<String, HeroCLass> {
    override fun toRemote(entity: HeroCLass) =
        entity.name.capitalize()

    override fun fromRemoteSingle(remote: String) =
        HeroCLass.values()
            .first { it.name.capitalize() == remote }
}
