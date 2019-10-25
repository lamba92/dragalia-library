package com.github.lamba92.dragalialost.data.mappers

import com.github.lamba92.dragalialost.data.rawresponses.AbilityGroupJSON
import com.github.lamba92.dragalialost.domain.entities.enums.AbilityType

class AbilityTypeMapper : SingleFromRemoteMapper<AbilityGroupJSON, AbilityType> {
    override fun fromRemoteSingle(remote: AbilityGroupJSON) = when (remote.GroupName.toLowerCase()) {
        "attack" -> AbilityType.ATTACK
        "defense" -> AbilityType.DEFENSE
        "recovery" -> AbilityType.RECOVERY
        "support" -> AbilityType.SUPPORT
        "anti-poison", "anti-burn", "anti-freeze", "anti-paralysis",
        "anti-blindness", "anti-stun", "anti-curse", "anti-bog",
        "anti-sleep" -> AbilityType.AFFLICTIONS_RESISTANCE
        "anti-flame", "anti-water", "anti-wind",
        "anti-light", "anti-shadow" -> AbilityType.ELEMENT_RESISTANCE
        "anti-class" -> AbilityType.ANTI_CLASS
        "class banes" -> AbilityType.CLASS_BANES
        "event perks" -> AbilityType.EVENT_PERKS
        "other" -> AbilityType.OTHER
        else -> throw IllegalStateException("Ability group \"${remote.GroupName}\" is not known!")
    }
}
