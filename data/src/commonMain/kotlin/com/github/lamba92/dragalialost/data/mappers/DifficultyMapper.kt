package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.domain.entities.enums.Source

class DifficultyMapper : SingleFromRemoteMapper<String, Source.Quest.Difficulty> {
    override fun fromRemoteSingle(remote: String) = when (remote.toLowerCase()) {
        "normal" -> Source.Quest.Difficulty.NORMAL
        "hard" -> Source.Quest.Difficulty.HARD
        else -> throw IllegalArgumentException("Difficulty \"$remote\" cannot be handled yet!")
    }
}
