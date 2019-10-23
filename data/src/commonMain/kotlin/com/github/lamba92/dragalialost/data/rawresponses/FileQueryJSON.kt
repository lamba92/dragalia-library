package com.github.lamba92.dragalialost.data.rawresponses

import kotlinx.serialization.Serializable

@Serializable
data class FileQueryJSON(
    val batchcomplete: String? = null,
    val `continue`: ContinueJSON? = null,
    val query: QueryJSON
)

@Serializable
data class QueryJSON(
    val normalized: List<NormalizedJSON>? = null,
    val pages: Map<String, PageInfoJSON>
)

@Serializable
data class PageInfoJSON(
    val pageid: Int,
    val ns: Int,
    val title: String,
    val imagerepository: String,
    val imageinfo: List<ImageInfoJSON>
)

@Serializable
data class ImageInfoJSON(
    val url: String,
    val descriptionurl: String,
    val descriptionshorturl: String
)

@Serializable
data class NormalizedJSON(val from: String, val to: String)

@Serializable
data class ContinueJSON(val iistart: String, val `continue`: String)
