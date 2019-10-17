package com.github.lamba92.dragalia.mappers

import com.github.lamba92.dragalia.entities.WyrmprintEntity
import com.github.lamba92.dragalia.rawresponses.WyrmprintCargoJSON
import com.github.lamba92.dragalia.rawresponses.WyrmprintJSON

class WyrmprintsMapper : MultipleFromRemoteMapper<WyrmprintCargoJSON, WyrmprintJSON, WyrmprintEntity> {
    override fun fromRemoteSingle(remote: WyrmprintJSON): WyrmprintEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fromRemoteMultiple(remote: WyrmprintCargoJSON) =
        remote.cargoquery.map { fromRemoteSingle(it.title) }

}
