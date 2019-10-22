package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.domain.entities.enums.Source

class SourceMapper(
    private val difficultyMapper: DifficultyMapper
) : SingleFromRemoteMapper<String, List<Source>> {

    private val storyWithChapterRegex = Regex("Story - Chapter (\\d*)")
    private val baseEventRegex = Regex("\\[\\[?(.*)\\]\\[? ?(Event)?")
    private val treasureTradeEventRegex = Regex("\\[\\[?(.*)\\]\\[? Event Treasure Trade")
    private val summonShowcaseEventRegex = Regex("\\[\\[?(.*)\\]\\[? Summon Showcase")
    private val summonShowcaseEvent2Regex = Regex("\\[\\[?(.*) \\(Summon Showcase\\)\\]\\[? Summon Showcase")
    private val campaignRegex = Regex("\\[\\[Campaign_Quests|Campaign: (\\w*)\\]\\]")
    private val storyRegexWithEvent = Regex("Clear Story (\\w) of the \\[\\[(.*)\\]\\] Event")
    private val storyRegexWithEvent2 = Regex("\\[\\[(.*)\\]\\] Event Story Completion Reward")

    override fun fromRemoteSingle(remote: String) = remote
        .replace("&lt;br&gt;\n", "")
        .split("\n")
        .map {
            when {
                it == "Story" -> Source.Story()
                it == "Story - Prologue" -> Source.Story(0)
                it == "Summoning" -> Source.Summoning()
                it == "Quest Drop" -> Source.QuestDrop
                "Shop" in it -> Source.Shop
                campaignRegex.matches(it) -> Source.Quest(difficultyMapper(campaignRegex.find(it)!!.groupValues[1]))
                storyWithChapterRegex.matches(it) -> Source.Story(storyWithChapterRegex.find(it)!!.groupValues[1].toInt())
                treasureTradeEventRegex.matches(it) -> Source.TreasureTrade(treasureTradeEventRegex.find(it)!!.groupValues[1])
                baseEventRegex.matches(it) -> Source.Event(baseEventRegex.find(it)!!.groupValues[1])
                summonShowcaseEventRegex.matches(it) -> Source.Summoning(summonShowcaseEventRegex.find(it)!!.groupValues[1])
                summonShowcaseEvent2Regex.matches(it) -> Source.Summoning(summonShowcaseEventRegex.find(it)!!.groupValues[1])
                storyRegexWithEvent.matches(it) -> Source.Story(
                    storyRegexWithEvent.find(it)!!.groupValues[1].toInt(),
                    storyRegexWithEvent.find(it)!!.groupValues[2]
                )
                storyRegexWithEvent2.matches(it) -> Source.Story(null, storyRegexWithEvent2.find(it)!!.groupValues[1])
                else -> throw IllegalArgumentException("Source \"$it\" has not been handled yet!\nOriginal Source string: $remote")
            }
        }

}
