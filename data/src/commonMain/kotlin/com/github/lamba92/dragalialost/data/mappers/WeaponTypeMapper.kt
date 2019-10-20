package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.domain.entities.enums.WeaponType

class WeaponTypeMapper : SingleToRemoteMapper<String, WeaponType>, SingleFromRemoteMapper<String, WeaponType> {

    override fun toRemote(entity: WeaponType) =
        entity.name.capitalize()

    override fun fromRemoteSingle(remote: String) =
        WeaponType.values()
            .first { it.name.toLowerCase() == remote.toLowerCase() }
}
