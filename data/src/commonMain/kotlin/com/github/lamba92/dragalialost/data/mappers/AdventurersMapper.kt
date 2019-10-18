package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.AdventurerCargoJSON
import com.github.lamba92.dragalialost.data.rawresponses.AdventurerJSON
import com.github.lamba92.dragalialost.domain.entities.AdventurerEntity

class AdventurersMapper : MultipleFromRemoteMapper<AdventurerCargoJSON, AdventurerJSON, AdventurerEntity> {

    override fun fromRemoteSingle(remote: AdventurerJSON): AdventurerEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fromRemoteMultiple(remote: AdventurerCargoJSON) =
        remote.cargoquery.map { fromRemoteSingle(it.title) }

}