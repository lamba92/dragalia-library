package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.ImageInfoJSON

class ImageMapper : SingleFromRemoteMapper<List<ImageInfoJSON>, List<String>> {
    override fun fromRemoteSingle(remote: List<ImageInfoJSON>) =
        remote.map { it.url }
}
