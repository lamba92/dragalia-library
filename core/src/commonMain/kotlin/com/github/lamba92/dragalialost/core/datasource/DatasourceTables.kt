package com.github.lamba92.dragalialost.core.datasource

import kotlinx.serialization.Serializable

@Serializable
enum class DatasourceTables(val tableName: String) {

    ADVENTURERS_TABLE("Adventurers"), DRAGONS_TABLE("Dragons"),
    WYRMPRINTS_TABLE("Wyrmprints"), WEAPONS_TABLE("Weapons"),
    ABILITIES_TABLE("Abilities"), CO_ABILITIES_TABLE("CoAbilities"),
    SKILLS_TABLE("Skills"), ABILITY_LIMITED_GROUPS_TABLE("AbilityLimitedGroup"),
    ABILITY_GROUPS_TABLE("AbilityGroup");

    override fun toString() = tableName

}
