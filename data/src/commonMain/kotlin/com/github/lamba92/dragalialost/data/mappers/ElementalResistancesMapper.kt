package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.AbilityJSON
import com.github.lamba92.dragalialost.data.utils.sanitize
import com.github.lamba92.dragalialost.domain.entities.enums.Element
import com.github.lamba92.dragalialost.domain.entities.support.ElementalResistances

class ElementalResistancesMapper : SingleFromRemoteMapper<AbilityJSON, ElementalResistances> {

    private val regex = Regex("Reduces (\\w*)( and )?(\\w*) damage taken by (\\d{1,3})%.?")

    override fun fromRemoteSingle(remote: AbilityJSON) = with(remote) {
        val groups = regex.find(Details.sanitize())?.groups
        val afflictions = ElementalResistances()
        if (groups != null) {

            val resValue = groups[4]!!.value.toInt()

            val element1 = Element.values().first { it.name.toLowerCase() in groups[1]!!.value.toLowerCase() }

            val element2 = groups[3]?.value?.let { eName ->
                if (eName.isNotBlank()) Element.values().first { it.name.toLowerCase() in eName.toLowerCase() }
                else null
            }

            afflictions[element1] = resValue
            element2?.let { afflictions[it] = resValue }
        }
        afflictions
    }

}
