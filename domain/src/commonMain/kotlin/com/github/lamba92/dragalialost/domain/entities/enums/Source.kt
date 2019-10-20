package com.github.lamba92.dragalialost.domain.entities.enums

sealed class Source {

    data class Story(val chapter: Int? = null) : Source() {
        override fun toString() = when (chapter) {
            null -> "Story"
            0 -> "Story - Prologue"
            else -> "Story - Chapter $chapter"
        }
    }

    data class Summoning(val eventName: String? = null) : Source() {
        override fun toString() =
            eventName?.let { "$it - Summon Showcase" } ?: "Summoning"
    }


    data class Event(val eventName: String) : Source() {
        override fun toString() =
            "$eventName - Event"
    }

}
