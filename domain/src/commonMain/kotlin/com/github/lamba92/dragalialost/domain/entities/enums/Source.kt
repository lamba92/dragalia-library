package com.github.lamba92.dragalialost.domain.entities.enums

sealed class Source {

    data class Story(val chapter: Int) : Source() {
        override fun toString() =
            "Story: Chapter - $chapter"
    }

    data class Summoning(val event: String? = null) : Source() {
        override fun toString() =
            event?.let { "$it Summon Showcase" } ?: "Summoning"
    }

}
