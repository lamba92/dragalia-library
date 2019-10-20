package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.domain.entities.enums.Availability

class AvailabilityMapper : SingleFromRemoteMapper<String, List<Availability>> {

    override fun fromRemoteSingle(remote: String) =
        remote.split(", ").map { Availability(it) }

}
