package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.AbilityGroupJSON
import com.github.lamba92.dragalialost.data.rawresponses.AbilityJSON
import com.github.lamba92.dragalialost.data.utils.sanitize
import com.github.lamba92.dragalialost.domain.entities.enums.Afflictions
import com.github.lamba92.dragalialost.domain.entities.support.AfflictionResistances

class AfflictionResistancesMapper(
    private val resistancePercentageMapper: ResistancePercentageMapper
) : SingleFromRemoteMapper<AfflictionResistancesMapper.Params, AfflictionResistances> {

    private val regex = Regex("Reduces susceptibility to (\\w*)( and )?(\\w*) by (\\d{2,3})%.?")

    override fun fromRemoteSingle(remote: Params): AfflictionResistances = with(remote) {
        val groups = regex.find(ability.Details.sanitize())?.groups

        val afflictions = AfflictionResistances()
        if (groups != null) {
            val resValue = resistancePercentageMapper(groups[4]!!.value)

            val element1 = try {
                Afflictions.values().first { it.name.toLowerCase() in groups[1]!!.value.toLowerCase() }
            } catch (e: NoSuchElementException) {
                when (groups[1]!!.value.toLowerCase()) {
                    "freezing" -> Afflictions.FREEZE
                    else -> throw e
                }
            }

            val element2 = groups[3]?.value?.let { eName ->
                if (eName.isNotBlank())
                    Afflictions.values().first { it.name.toLowerCase() in eName.toLowerCase() }
                else
                    null
            }

            afflictions[element1] = resValue
            element2?.let { afflictions[it] = resValue }
        }
        afflictions
    }

    data class Params(
        val ability: AbilityJSON,
        val group: AbilityGroupJSON
    )

}
