package com.github.lamba92.dragalialost.domain.entities.enums

sealed class Source {

    data class Story(val chapter: Int? = null, val event: String? = null) : Source() {
        override fun toString() = when (chapter) {
            null -> when (event) {
                null -> "Story"
                else -> "Story - Event $event"
            }
            0 -> "Story - Prologue"
            else -> when (event) {
                null -> "Story - Chapter $chapter"
                else -> "Story - Chapter $chapter | Event: $event"
            }
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

    data class Quest(val difficulty: Difficulty = Difficulty.NORMAL) : Source() {
        enum class Difficulty {
            NORMAL, HARD
        }

        override fun toString() =
            "Quest - Difficulty: ${difficulty.name.capitalize()}"
    }

    object QuestDrop : Source() {
        override fun toString() =
            "Quest Drop"
    }

    object Shop : Source() {
        override fun toString() =
            "Shop"
    }

    data class TreasureTrade(val eventName: String? = null) : Source() {
        override fun toString() =
            eventName?.let { "Treasure Trade - Event: $it" } ?: "Treasure Trade"
    }

    data class Other(val value: String) : Source()

}
