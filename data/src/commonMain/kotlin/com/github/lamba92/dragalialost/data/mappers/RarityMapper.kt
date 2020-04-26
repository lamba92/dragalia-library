package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.domain.entities.enums.Rarity
import com.github.lamba92.dragalialost.domain.entities.enums.Rarity.*

class RarityMapper : SingleToRemoteMapper<Int, Rarity>, SingleFromRemoteMapper<String, Rarity> {
    override fun toRemote(entity: Rarity) = when (entity) {
        TWO -> 2
        THREE -> 3
        FOUR -> 4
        FIVE -> 5
        SIX -> 6
    }

    override fun fromRemoteSingle(remote: String) = when (remote) {
        "2" -> TWO
        "3" -> THREE
        "4" -> FOUR
        "5" -> FIVE
        "6" -> SIX
        else -> throw IllegalArgumentException("Rarity $remote has not been managed yet")
    }

    fun fromRemoteSingle(remote: Int) = when (remote) {
        2 -> TWO
        3 -> THREE
        4 -> FOUR
        5 -> FIVE
        6 -> SIX
        else -> throw IllegalArgumentException("Rarity $remote has not been managed yet")
    }

    operator fun invoke(remote: Int) =
        fromRemoteSingle(remote)

}
