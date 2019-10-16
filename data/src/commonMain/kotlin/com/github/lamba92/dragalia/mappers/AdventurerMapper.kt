package com.github.lamba92.dragalia.mappers

import com.github.lamba92.dragalia.entities.AdventurerEntity
import com.github.lamba92.dragalia.rawresponses.AdventurerCargoJSON
import com.github.lamba92.dragalia.rawresponses.AdventurerJSON

class AdventurerMapper : MultipleFromRemoteMapper<AdventurerCargoJSON, AdventurerJSON, AdventurerEntity> {

    override fun fromRemoteSingle(remote: AdventurerJSON): AdventurerEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fromRemoteMultiple(remote: AdventurerCargoJSON) =
        remote.cargoquery.map { fromRemoteSingle(it.title) }

}