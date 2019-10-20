package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.domain.entities.enums.Source

class SourceMapper : SingleFromRemoteMapper<String, Source> {

    private val storyWithChapterRegex = Regex("Story - Chapter (\\d*)")
    private val baseEventRegex = Regex("\\[(.*)\\] Event")
    private val summonShowcaseEventRegex = Regex("\\[(.*)\\] Summon Showcase")

    override fun fromRemoteSingle(remote: String) = when {
        remote == "Story" -> Source.Story()
        remote == "Story - Prologue" -> Source.Story(0)
        remote == "Summoning" -> Source.Summoning()
        storyWithChapterRegex.matches(remote) -> Source.Story(storyWithChapterRegex.find(remote)!!.groupValues[1].toInt())
        baseEventRegex.matches(remote) -> Source.Event(baseEventRegex.find(remote)!!.groupValues[1])
        summonShowcaseEventRegex.matches(remote) -> Source.Summoning(summonShowcaseEventRegex.find(remote)!!.groupValues[1])
        else -> throw IllegalArgumentException("Source $remote has not been handled yet!")
    }

}
