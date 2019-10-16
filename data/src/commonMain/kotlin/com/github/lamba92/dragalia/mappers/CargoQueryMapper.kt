package com.github.lamba92.dragalia.mappers

import com.github.lamba92.dragalia.rawresponses.CargoJSON

interface CargoQueryMapper<Remote, Entity> :
    MultipleFromRemoteMapper<CargoJSON<Remote>, Remote, Entity> {

    override fun fromRemoteMultiple(remote: CargoJSON<Remote>) =
        remote.cargoquery.map { fromRemoteSingle(it.title) }

}