package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.AbilityJSON
import com.github.lamba92.dragalialost.data.utils.sanitize
import com.github.lamba92.dragalialost.domain.entities.enums.Affliction
import com.github.lamba92.dragalialost.domain.entities.support.AfflictionResistances

class AfflictionResistancesMapper(
    private val resistancePercentageMapper: ResistancePercentageMapper
) : SingleFromRemoteMapper<AbilityJSON, AfflictionResistances> {

    private val regex = Regex("Reduces susceptibility to (\\w*)( and )?(\\w*) by (\\d{2,3})%.?")

    override fun fromRemoteSingle(remote: AbilityJSON): AfflictionResistances = with(remote) {
        val groups = regex.find(Details.sanitize())?.groups

        val afflictions = AfflictionResistances()
        if (groups != null) {
            val resValue = resistancePercentageMapper(groups[4]!!.value)

            val element1 = try {
                Affliction.values().first { it.name.toLowerCase() in groups[1]!!.value.toLowerCase() }
            } catch (e: NoSuchElementException) {
                when (groups[1]!!.value.toLowerCase()) {
                    "freezing" -> Affliction.FREEZE
                    else -> throw e
                }
            }

            val element2 = groups[3]?.value?.let { eName ->
                if (eName.isNotBlank())
                    Affliction.values().first { it.name.toLowerCase() in eName.toLowerCase() }
                else
                    null
            }

            afflictions[element1] = resValue
            element2?.let { afflictions[it] = resValue }
        }
        afflictions
    }

}
