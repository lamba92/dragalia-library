package com.github.lamba92.dragalia.datasource.queries

class AbilityLimitedGroupsCargoQuery(
    val isEffectMIx: Boolean? = null,
    val maxLimitedValue: Int? = null,
    val abilityLimitedText: String? = null,
    name: String? = null
) : NamedCargoQuery(name)