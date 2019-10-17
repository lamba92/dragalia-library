package com.github.lamba92.dragalia.mappers

import com.github.lamba92.dragalia.entities.DragonEntity
import com.github.lamba92.dragalia.rawresponses.DragonCargoJSON
import com.github.lamba92.dragalia.rawresponses.DragonJSON

class DragonsMapper : MultipleFromRemoteMapper<DragonCargoJSON, DragonJSON, DragonEntity> {

    override fun fromRemoteSingle(remote: DragonJSON): DragonEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fromRemoteMultiple(remote: DragonCargoJSON) =
        remote.cargoquery.map { fromRemoteSingle(it.title) }

}
