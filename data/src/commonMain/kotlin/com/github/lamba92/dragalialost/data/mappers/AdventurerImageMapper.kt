package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.ImageInfoJSON
import com.github.lamba92.dragalialost.domain.entities.enums.Rarity

class AdventurerImageMapper(
    private val rarityMapper: RarityMapper
) : SingleFromRemoteMapper<List<Pair<Int, ImageInfoJSON?>>, Map<Rarity, String?>> {
    override fun fromRemoteSingle(remote: List<Pair<Int, ImageInfoJSON?>>) =
        remote.map { (rarity, json) ->
            rarityMapper(rarity) to json?.url
        }.toMap()

}
