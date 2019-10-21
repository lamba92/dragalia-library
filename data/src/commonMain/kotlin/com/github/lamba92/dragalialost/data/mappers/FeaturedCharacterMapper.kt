package com.github.lamba92.dragalialost.data.mappers

class FeaturedCharacterMapper : SingleFromRemoteMapper<String, List<String>> {
    override fun fromRemoteSingle(remote: String) =
        remote.split(", ")
}
