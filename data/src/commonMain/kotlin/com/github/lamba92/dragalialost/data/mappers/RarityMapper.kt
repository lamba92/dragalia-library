package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.domain.entities.enums.Rarity
import com.github.lamba92.dragalialost.domain.entities.enums.Rarity.*

class RarityMapper : SingleToRemoteMapper<String, Rarity>, SingleFromRemoteMapper<String, Rarity> {
    override fun toRemote(entity: Rarity) = when (entity) {
        TWO -> "2"
        THREE -> "3"
        FOUR -> "4"
        FIVE -> "5"
    }

    override fun fromRemoteSingle(remote: String) = when (remote) {
        "1" -> TWO
        "2" -> TWO
        "3" -> TWO
        "4" -> TWO
        else -> throw IllegalArgumentException("Rarity $remote has not been managed yet")
    }

}
