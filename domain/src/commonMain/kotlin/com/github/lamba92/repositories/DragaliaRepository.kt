package com.github.lamba92.repositories

import com.github.lamba92.entities.AdventurerEntity
import com.github.lamba92.entities.DragonEntity
import com.github.lamba92.entities.WyrmprintEntity
import com.github.lamba92.entities.enums.*

interface DragaliaRepository {

    suspend fun searchAdventurersByName(query: String): List<AdventurerEntity>
    suspend fun searchAdventurersByRarity(rarity: Rarity): List<AdventurerEntity>
    suspend fun searchAdventurersByElement(element: Element): List<AdventurerEntity>
    suspend fun searchAdventurersByHeroClass(heroCLass: HeroCLass): List<AdventurerEntity>
    suspend fun searchAdventurersByWeaponType(weaponType: WeaponType): List<AdventurerEntity>

    suspend fun searchDragonsByName(query: String): List<DragonEntity>
    suspend fun searchDragonsByRarity(rarity: Rarity): List<DragonEntity>
    suspend fun searchDragonsByElement(element: Element): List<DragonEntity>

    suspend fun searchWyrmprintByName(query: String): List<WyrmprintEntity>
    suspend fun searchWyrmprintByRarity(rarity: Rarity): List<WyrmprintEntity>
    suspend fun searchWyrmprintByAbilityType(wyrmprintAbilityType: WyrmprintAbilityType): List<WyrmprintEntity>

}