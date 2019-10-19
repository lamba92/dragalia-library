package com.github.lamba92.dragalialost.data.rawresponses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeaponCargoJSON(val cargoquery: List<WeaponsNestedTitleJson>)

@Serializable
data class WeaponsNestedTitleJson(val title: WeaponJSON)

@Serializable
data class WeaponJSON(
    val Abilities11: String,
    val Abilities21: String,
    val AssembleCoin: String,
    val Availability: String,
    val AvailabilityId: String,
    val BaseId: String,
    val CraftGroupId: String,
    val CraftMaterial1: String,
    val CraftMaterial2: String,
    val CraftMaterial3: String,
    val CraftMaterial4: String,
    val CraftMaterial5: String,
    val CraftMaterialQuantity1: String,
    val CraftMaterialQuantity2: String,
    val CraftMaterialQuantity3: String,
    val CraftMaterialQuantity4: String,
    val CraftMaterialQuantity5: String,
    val CraftMaterialType1: String,
    val CraftMaterialType2: String,
    val CraftMaterialType3: String,
    val CraftMaterialType4: String,
    val CraftMaterialType5: String,
    val CraftNodeId: String,
    val DisassembleCoin: String,
    val DisassembleCost: String,
    val ElementalType: String,
    val ElementalTypeId: String,
    val FlavorText: String,
    val FormId: String,
    val FortCraftLevel: String,
    val Id: String,
    val IsPlayable: String,
    val MainWeaponId: String,
    val MainWeaponQuantity: String,
    val MaxAtk: String,
    val MaxHp: String,
    val MinAtk: String,
    val MinHp: String,
    val Obtain: String,
    val ParentCraftNodeId: String,
    val Rarity: String,
    val ReleaseDate: String,
    val ReleaseDate__precision: String? = null,
    @SerialName("ReleaseDate  precision") val ReleaseDate__precision2: String? = null,
    val SellCoin: String,
    val SellDewPoint: String,
    val Skill: String,
    val SkillDesc: String,
    val SkillName: String,
    val Type: String,
    val TypeId: String,
    val VariationId: String,
    val WeaponName: String,
    val WeaponNameJP: String
) : CargoQueryable