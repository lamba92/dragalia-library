package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.domain.entities.enums.Element

class ElementMapper : SingleToRemoteMapper<String, Element>, SingleFromRemoteMapper<String, Element> {

    override fun toRemote(entity: Element) =
        entity.name.capitalize()

    override fun fromRemoteSingle(remote: String) =
        Element.values().first { it.name.capitalize() == remote }
}
