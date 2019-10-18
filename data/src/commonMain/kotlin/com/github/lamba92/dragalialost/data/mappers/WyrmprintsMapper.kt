package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.WyrmprintCargoJSON
import com.github.lamba92.dragalialost.data.rawresponses.WyrmprintJSON
import com.github.lamba92.dragalialost.domain.entities.WyrmprintEntity

class WyrmprintsMapper : MultipleFromRemoteMapper<WyrmprintCargoJSON, WyrmprintJSON, WyrmprintEntity> {
    override fun fromRemoteSingle(remote: WyrmprintJSON): WyrmprintEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fromRemoteMultiple(remote: WyrmprintCargoJSON) =
        remote.cargoquery.map { fromRemoteSingle(it.title) }

}
