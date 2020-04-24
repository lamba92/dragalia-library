package com.github.lamba92.dragalialost.core.datasource

import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasourceCache
import com.github.lamba92.dragalialost.data.datasource.queries.*
import com.github.lamba92.dragalialost.data.rawresponses.*
import com.github.lamba92.dragalialost.data.utils.MongoDBInitializer
import com.mongodb.MongoWriteException
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase

class MongoDBGamepediaCache private constructor(db: CoroutineDatabase) : GamepediaDatasourceCache {

    companion object : MongoDBInitializer<MongoDBGamepediaCache> {

        override suspend fun initCollections(db: CoroutineDatabase) = listOf(
            "adventurers", "dragons", "wyrmprints", "weapons", "abilities",
            "coAbilities", "skillsById", "skillsByName", "abilityLimitedGroup", "abilityGroup", "adventurerIcons",
            "adventurerPortraits", "dragonIcons", "dragonPortraits", "wyrmprintIcons", "wyrmprintPortraits",
            "abilityIcons", "coAbilityIcons", "skillIcons"
        ).filter { it !in db.listCollectionNames() }
            .forEach { db.createCollection(it) }

        override suspend fun initialize(db: CoroutineDatabase): MongoDBGamepediaCache {
            initCollections(db)
            return MongoDBGamepediaCache(db)
        }

        override fun initializeBlocking(db: CoroutineDatabase) =
            runBlocking {
                initialize(db)
            }
    }

    @Serializable
    private data class AdventurerDocument(val _id: String, val data: AdventurerJSON)

    @Serializable
    private data class DragonDocument(val _id: String, val data: DragonJSON)

    @Serializable
    private data class WyrmprintDocument(val _id: String, val data: WyrmprintJSON)

    @Serializable
    private data class WeaponDocument(val _id: String, val data: WeaponJSON)

    @Serializable
    private data class AbilityDocument(val _id: String, val data: AbilityJSON)

    @Serializable
    private data class CoAbilityDocument(val _id: String, val data: CoAbilityJSON)

    @Serializable
    private data class SkillDocument(val _id: String, val data: SkillJSON)

    @Serializable
    private data class AbilityLimitedGroupDocument(val _id: String, val data: AbilityLimitedGroupJSON)

    @Serializable
    private data class AbilityGroupDocument(val _id: String, val data: AbilityGroupJSON)

    @Serializable
    private data class ImageInfoDocument(val _id: String, val data: ImageInfoJSON)

    private val adventurersCollection =
        db.getCollection<AdventurerDocument>("adventurers")
    private val dragonsCollection =
        db.getCollection<DragonDocument>("dragons")
    private val wyrmprintsCollection =
        db.getCollection<WyrmprintDocument>("wyrmprints")
    private val weaponsCollection =
        db.getCollection<WeaponDocument>("weapons")
    private val abilitiesCollection =
        db.getCollection<AbilityDocument>("abilities")
    private val coAbilitiesCollection =
        db.getCollection<CoAbilityDocument>("coAbilities")
    private val skillsByIdCollection =
        db.getCollection<SkillDocument>("skillsById")
    private val skillsByNameCollection =
        db.getCollection<SkillDocument>("skillsByName")
    private val abilityLimitedGroupsCollection =
        db.getCollection<AbilityLimitedGroupDocument>("abilityLimitedGroup")
    private val abilityGroupsCollection =
        db.getCollection<AbilityGroupDocument>("abilityGroup")

    private val adventurerIconsCollection =
        db.getCollection<ImageInfoDocument>("adventurerIcons")
    private val adventurerPortraitsCollection =
        db.getCollection<ImageInfoDocument>("adventurerPortraits")
    private val dragonIconsCollection =
        db.getCollection<ImageInfoDocument>("dragonIcons")
    private val dragonPortraitsCollection =
        db.getCollection<ImageInfoDocument>("dragonPortraits")
    private val wyrmprintIconsCollection =
        db.getCollection<ImageInfoDocument>("wyrmprintIcons")
    private val wyrmprintPortraitsCollection =
        db.getCollection<ImageInfoDocument>("wyrmprintPortraits")
    private val abilityIconsCollection =
        db.getCollection<ImageInfoDocument>("abilityIcons")
    private val coAbilityIconsCollection =
        db.getCollection<ImageInfoDocument>("coAbilityIcons")
    private val skillIconsCollection =
        db.getCollection<ImageInfoDocument>("skillIcons")

    override suspend fun searchAdventurerIds(query: AdventurersCargoQuery, limit: Int): List<AdventurerIdJSON>? =
        null

    override suspend fun searchDragonIds(query: DragonsCargoQuery, limit: Int): List<String>? =
        null

    override suspend fun searchWyrmprintIds(query: WyrmprintsCargoQuery, limit: Int): List<String>? =
        null

    override suspend fun searchWeaponIds(query: WeaponsCargoQuery, limit: Int): List<String>? =
        null

    override suspend fun searchAbilityIds(query: AbilitiesCargoQuery, limit: Int): List<String>? =
        null

    override suspend fun searchCoAbilityIds(query: CoAbilitiesCargoQuery, limit: Int): List<String>? =
        null

    override suspend fun searchSkillIds(query: SkillsCargoQuery, limit: Int): List<String>? =
        null

    override suspend fun searchAbilityLimitedGroupIds(
        query: AbilityLimitedGroupsCargoQuery,
        limit: Int
    ): List<String>? =
        null

    override suspend fun getAdventurerByIds(id: String, variationId: String) =
        adventurersCollection.findOneById("${id}_$variationId")?.data

    override suspend fun getDragonById(id: String) =
        dragonsCollection.findOneById(id)?.data

    override suspend fun getWyrmprintById(id: String) =
        wyrmprintsCollection.findOneById(id)?.data

    override suspend fun getWeaponById(id: String) =
        weaponsCollection.findOneById(id)?.data

    override suspend fun getAbilityById(id: String) =
        abilitiesCollection.findOneById(id)?.data

    override suspend fun getCoAbilityById(id: String) =
        coAbilitiesCollection.findOneById(id)?.data

    override suspend fun getSkillById(id: String) =
        skillsByIdCollection.findOneById(id)?.data

    override suspend fun getSkillByName(name: String) =
        skillsByNameCollection.findOneById(name)?.data

    override suspend fun getAbilityLimitedGroupById(id: String) =
        abilityLimitedGroupsCollection.findOneById(id)?.data

    override suspend fun getAbilityGroupsByGroupId(id: String) =
        abilityGroupsCollection.findOneById(id)?.data

    override suspend fun getAdventurerIconById(id: String, variationId: String, rarity: Int) =
        adventurerIconsCollection.findOneById("${id}_${variationId}_$rarity")?.data

    override suspend fun getAdventurerPortraitById(id: String, variationId: String, rarity: Int) =
        adventurerPortraitsCollection.findOneById("${id}_${variationId}_$rarity")?.data

    override suspend fun getDragonIconById(id: String) =
        dragonIconsCollection.findOneById(id)?.data

    override suspend fun getDragonPortraitById(id: String) =
        dragonPortraitsCollection.findOneById(id)?.data

    override suspend fun getWyrmprintIconByIds(id: String, vestige: Int) =
        wyrmprintIconsCollection.findOneById("${id}_$vestige")?.data

    override suspend fun getWyrmprintPortraitByIds(id: String, vestige: Int) =
        wyrmprintPortraitsCollection.findOneById("${id}_$vestige")?.data

    override suspend fun getAbilityIconByFileName(fileName: String) =
        abilityIconsCollection.findOneById(fileName)?.data

    override suspend fun getCoAbilityIconByFileName(fileName: String) =
        coAbilityIconsCollection.findOneById(fileName)?.data

    override suspend fun getSkillIconByIconName(fileName: String) =
        skillIconsCollection.findOneById(fileName)?.data

    override suspend fun cacheAdventurerCargoQuery(
        query: AdventurersCargoQuery,
        limit: Int,
        data: List<AdventurerIdJSON>
    ) =
        true

    override suspend fun cacheDragonCargoQuery(query: DragonsCargoQuery, limit: Int, data: List<String>) =
        true

    override suspend fun cacheWyrmprintCargoQuery(
        query: WyrmprintsCargoQuery,
        limit: Int,
        data: List<String>
    ) =
        true

    override suspend fun cacheWeaponCargoQuery(query: WeaponsCargoQuery, limit: Int, data: List<String>) =
        true

    override suspend fun cacheAbilityCargoQuery(query: AbilitiesCargoQuery, limit: Int, data: List<String>) =
        true

    override suspend fun cacheCoAbilityCargoQuery(
        query: CoAbilitiesCargoQuery,
        limit: Int,
        data: List<String>
    ) =
        true

    override suspend fun cacheSkillCargoQuery(query: SkillsCargoQuery, limit: Int, data: List<String>) =
        true

    override suspend fun cacheAbilityLimitedGroupCargoQuery(
        query: AbilityLimitedGroupsCargoQuery,
        limit: Int,
        data: List<String>
    ) =
        true

//    private suspend fun <T> CoroutineClient.transaction(action: suspend (ClientSession) -> T) =
//        startSession().let {
//            it.startTransaction()
//            val r = action(it)
//            it.commitTransactionAndAwait()
//            r
//        }

    private suspend fun <T : Any> CoroutineCollection<T>.unsafeInsertOrUpdate(id: Any, document: T) {
        try {
            insertOne(document)
        } catch (e: MongoWriteException) {
            updateOneById(id, document)
        }
    }

    override suspend fun cacheAbilityGroupsByGroupId(groupId: String, data: AbilityGroupJSON): Boolean {
        abilityGroupsCollection.unsafeInsertOrUpdate(groupId, AbilityGroupDocument(groupId, data))
        return true
    }

    override suspend fun cacheAdventurerByIds(id: String, variationId: String, data: AdventurerJSON): Boolean {
        adventurersCollection.unsafeInsertOrUpdate("${id}_$variationId", AdventurerDocument("${id}_$variationId", data))
        return true
    }

    override suspend fun cacheDragonById(id: String, data: DragonJSON): Boolean {
        dragonsCollection.unsafeInsertOrUpdate(id, DragonDocument(id, data))
        return true
    }

    override suspend fun cacheWyrmprintById(id: String, data: WyrmprintJSON): Boolean {
        wyrmprintsCollection.unsafeInsertOrUpdate(id, WyrmprintDocument(id, data))
        return true
    }

    override suspend fun cacheWeaponById(id: String, data: WeaponJSON): Boolean {
        weaponsCollection.unsafeInsertOrUpdate(id, WeaponDocument(id, data))
        return true
    }

    override suspend fun cacheAbilityById(id: String, data: AbilityJSON): Boolean {
        abilitiesCollection.unsafeInsertOrUpdate(id, AbilityDocument(id, data))
        return true
    }

    override suspend fun cacheCoAbilityById(id: String, data: CoAbilityJSON): Boolean {
        coAbilitiesCollection.unsafeInsertOrUpdate(id, CoAbilityDocument(id, data))
        return true
    }

    override suspend fun cacheSkillById(id: String, data: SkillJSON): Boolean {
        skillsByIdCollection.unsafeInsertOrUpdate(id, SkillDocument(id, data))
        return true
    }

    override suspend fun cacheSkillByName(name: String, data: SkillJSON): Boolean {
        skillsByNameCollection.unsafeInsertOrUpdate(name, SkillDocument(name, data))
        return true
    }

    override suspend fun cacheAbilityLimitedGroupById(id: String, data: AbilityLimitedGroupJSON): Boolean {
        abilityLimitedGroupsCollection.unsafeInsertOrUpdate(id, AbilityLimitedGroupDocument(id, data))
        return true
    }

    override suspend fun cacheAdventurerIconById(
        id: String,
        variationId: String,
        rarity: Int,
        data: ImageInfoJSON
    ): Boolean {
        adventurerIconsCollection.updateOneById(
            "${id}_${variationId}_$rarity",
            ImageInfoDocument("${id}_${variationId}_$rarity", data)
        )
        return true
    }

    override suspend fun cacheAdventurerPortraitById(
        id: String,
        variationId: String,
        rarity: Int,
        data: ImageInfoJSON
    ): Boolean {
        adventurerPortraitsCollection.updateOneById(
            "${id}_${variationId}_$rarity",
            ImageInfoDocument("${id}_${variationId}_$rarity", data)
        )
        return true
    }

    override suspend fun cacheDragonIconById(id: String, data: ImageInfoJSON): Boolean {
        dragonIconsCollection.unsafeInsertOrUpdate(id, ImageInfoDocument(id, data))
        return true
    }

    override suspend fun cacheDragonPortraitById(id: String, data: ImageInfoJSON): Boolean {
        dragonPortraitsCollection.unsafeInsertOrUpdate(id, ImageInfoDocument(id, data))
        return true
    }

    override suspend fun cacheWyrmprintIconByIds(id: String, vestige: Int, data: ImageInfoJSON): Boolean {
        wyrmprintIconsCollection.unsafeInsertOrUpdate("${id}_$vestige", ImageInfoDocument("${id}_$vestige", data))
        return true
    }

    override suspend fun cacheWyrmprintPortraitByIds(id: String, vestige: Int, data: ImageInfoJSON): Boolean {
        wyrmprintPortraitsCollection.unsafeInsertOrUpdate("${id}_$vestige", ImageInfoDocument("${id}_$vestige", data))
        return true
    }

    override suspend fun cacheAbilityIconByFileName(fileName: String, data: ImageInfoJSON): Boolean {
        abilityIconsCollection.unsafeInsertOrUpdate(fileName, ImageInfoDocument(fileName, data))
        return true
    }

    override suspend fun cacheCoAbilityIconByFileName(fileName: String, data: ImageInfoJSON): Boolean {
        coAbilityIconsCollection.unsafeInsertOrUpdate(fileName, ImageInfoDocument(fileName, data))
        return true
    }

    override suspend fun cacheSkillIconByIconName(fileName: String, data: ImageInfoJSON): Boolean {
        skillIconsCollection.unsafeInsertOrUpdate(fileName, ImageInfoDocument(fileName, data))
        return true
    }

    override suspend fun invalidateCache() {
        listOf(
            adventurersCollection, dragonsCollection, wyrmprintsCollection, weaponsCollection, abilitiesCollection,
            coAbilitiesCollection, skillsByIdCollection, skillsByNameCollection, abilityLimitedGroupsCollection,
            abilityGroupsCollection, adventurerIconsCollection, adventurerPortraitsCollection, dragonIconsCollection,
            dragonPortraitsCollection, wyrmprintIconsCollection, wyrmprintPortraitsCollection, abilityIconsCollection,
            coAbilityIconsCollection, skillIconsCollection
        ).asFlow().onEach {
            it.deleteMany()
        }.collect()
    }
}
