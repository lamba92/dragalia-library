package com.github.lamba92.dragalialost.data.rawresponses

import kotlinx.serialization.Serializable

@Serializable
data class SkillCargoJSON(val cargoquery: List<SkillNestedTitleJson>)

@Serializable
data class SkillNestedTitleJson(val title: SkillJSON)

@Serializable
data class SkillJSON(
    val CrisisModifier: String?,
    val Description1: String,
    val Description2: String,
    val Description3: String?,
    val Description4: String?,
    val IframeDuration: String,
    val IsAffectedByTension: String,
    val Name: String,
    val SkillId: String,
    val MaxSkillLevel: Int,
    val SkillLv1IconName: String,
    val SkillLv2IconName: String,
    val SkillLv3IconName: String,
    val SkillLv4IconName: String,
    val Sp: String,
    val SPLv2: String,
    val SpRegen: String,
    val MaxAmmo: String,
    val AmmoUsage: String
) : CargoQueryable
