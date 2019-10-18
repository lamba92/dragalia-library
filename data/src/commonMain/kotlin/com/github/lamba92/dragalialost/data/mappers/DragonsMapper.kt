package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.DragonCargoJSON
import com.github.lamba92.dragalialost.data.rawresponses.DragonJSON
import com.github.lamba92.dragalialost.domain.entities.DragonEntity


class DragonsMapper : MultipleFromRemoteMapper<DragonCargoJSON, DragonJSON, DragonEntity> {

    override fun fromRemoteSingle(remote: DragonJSON): DragonEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fromRemoteMultiple(remote: DragonCargoJSON) =
        remote.cargoquery.map { fromRemoteSingle(it.title) }

}
