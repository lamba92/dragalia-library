package com.github.lamba92.dragalialost.core.datasource

import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasourceCache
import com.github.lamba92.dragalialost.data.rawresponses.*

actual class GamepediaCacheImplementation : GamepediaDatasourceCache {
    override suspend fun getAdventurersById(id: String): AdventurerJSON? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getDragonsById(id: String): DragonJSON? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getWyrmprintsById(id: String): WyrmprintJSON? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getWeaponsById(id: String): WeaponJSON? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAbilitiesById(id: String): AbilityJSON? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getCoAbilitiesById(id: String): CoAbilityJSON? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getSkillsById(id: String): SkillJSON? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAbilityLimitedGroupsById(id: String): AbilityLimitedGroupJSON? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun cacheAdventurersById(id: String, data: AdventurerJSON): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun cacheDragonsById(id: String, data: DragonJSON): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun cacheWyrmprintsById(id: String, data: WyrmprintJSON): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun cacheWeaponsById(id: String, data: WeaponJSON): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun cacheAbilitiesById(id: String, data: AbilityJSON): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun cacheCoAbilitiesById(id: String, data: CoAbilityJSON): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun cacheSkillsById(id: String, data: SkillJSON): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun cacheAbilityLimitedGroupsById(id: String, data: AbilityLimitedGroupJSON): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun invalidateCache() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}