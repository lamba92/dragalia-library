package com.github.lamba92.dragalia.repositories

import com.github.lamba92.dragalia.entities.AdventurerEntity
import com.github.lamba92.dragalia.entities.DragonEntity
import com.github.lamba92.dragalia.entities.WyrmprintEntity
import com.github.lamba92.dragalia.entities.enums.*

interface DragaliaRepository {

    suspend fun searchAdventurers(
        name: String?,
        weaponType: WeaponType?,
        rarity: Rarity?,
        element: Element?,
        heroCLass: HeroCLass?
    ): List<AdventurerEntity>

    suspend fun searchDragons(
        name: String,
        rarity: Rarity,
        element: Element
    ): List<DragonEntity>

    suspend fun searchWyrmprint(
        name: String,
        rarity: Rarity,
        wyrmprintAbilityType: WyrmprintAbilityType
    ): List<WyrmprintEntity>

}