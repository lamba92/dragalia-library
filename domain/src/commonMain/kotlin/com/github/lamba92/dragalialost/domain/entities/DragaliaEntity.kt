package com.github.lamba92.dragalialost.domain.entities

import com.github.lamba92.dragalialost.domain.entities.enums.AbilityType
import com.github.lamba92.dragalialost.domain.entities.enums.Availability
import com.github.lamba92.dragalialost.domain.entities.enums.Rarity
import com.github.lamba92.dragalialost.domain.entities.enums.Source
import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime

interface DragaliaEntity {

    val id: DragaliaId
    val name: String
    val maxLevel: Int
    val hp: Int
    val strength: Int
    val baseMinMight: Int
    val baseMaxMight: Int
    val baseRarity: Rarity
    val obtainedFrom: List<Source>
    val releaseDate: DateTime
    val availability: List<Availability>
    val icon: String?
    val artwork: String?
    val abilityTypes: Set<AbilityType>

    companion object {
        const val WYRMPRINTS_MAX_LVL = 100
        const val DRAGON_BOND_MIGHT_PER_LEVEL = 10
        const val DRAGONS_MAX_LVL = 100
        val DATE_TIME_FORMAT = DateFormat.FORMAT_DATE
        const val FORCE_STRIKE_LVL2_MIGHT = 120
        const val ADVENTURERS_MAX_LVL = 80
        const val ADVENTURER_SKILL_LIL1_MIGHT = 100
        const val ADVENTURER_SKILL_LVL2_MIGHT = 200
        const val ADVENTURER_SKILL_LVL3_MIGHT = 300
        const val DRAGON_SKILL_LIL1_MIGHT = 50
        const val DRAGON_SKILL_LVL2_MIGHT = 100
    }

}
