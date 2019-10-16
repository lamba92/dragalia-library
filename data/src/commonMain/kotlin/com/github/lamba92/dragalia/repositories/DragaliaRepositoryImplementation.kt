package com.github.lamba92.dragalia.repositories

import com.github.lamba92.dragalia.datasource.GamepediaDatasource
import com.github.lamba92.dragalia.entities.AdventurerEntity
import com.github.lamba92.dragalia.entities.DragonEntity
import com.github.lamba92.dragalia.entities.WyrmprintEntity
import com.github.lamba92.dragalia.entities.enums.*

class DragaliaRepositoryImplementation(
    private val gamepediaDatasource: GamepediaDatasource
) : DragaliaRepository {
    override suspend fun searchAdventurers(
        name: String?,
        weaponType: WeaponType?,
        rarity: Rarity?,
        element: Element?,
        heroCLass: HeroCLass?
    ): List<AdventurerEntity> =
        TODO()

    override suspend fun searchDragons(name: String, rarity: Rarity, element: Element): List<DragonEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun searchWyrmprint(
        name: String,
        rarity: Rarity,
        wyrmprintAbilityType: WyrmprintAbilityType
    ): List<WyrmprintEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}