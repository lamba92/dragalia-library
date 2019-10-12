package com.github.lamba92.entities.enums

sealed class Source {

    data class Story(val chapter: Int) {
        override fun toString() =
            "Story: Chapter - $chapter"
    }

    data class Summoning(val event: String) {
        override fun toString() =
            "$event Summon Showcase"
    }

}
