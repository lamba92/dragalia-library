package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.domain.entities.enums.Rarity
import com.github.lamba92.dragalialost.domain.entities.enums.Rarity.*

class RarityMapper : SingleToRemoteMapper<Int, Rarity> {
    override fun toRemote(entity: Rarity) = when (entity) {
        TWO -> 2
        THREE -> 3
        FOUR -> 4
        FIVE -> 5
    }

}
