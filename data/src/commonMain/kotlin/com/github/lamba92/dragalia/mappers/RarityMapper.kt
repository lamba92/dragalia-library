package com.github.lamba92.dragalia.mappers

import com.github.lamba92.dragalia.entities.enums.Rarity
import com.github.lamba92.dragalia.entities.enums.Rarity.*

class RarityMapper : SingleToRemoteMapper<Int, Rarity> {
    override fun toRemote(entity: Rarity) = when (entity) {
        TWO -> 2
        THREE -> 3
        FOUR -> 4
        FIVE -> 5
    }

}
