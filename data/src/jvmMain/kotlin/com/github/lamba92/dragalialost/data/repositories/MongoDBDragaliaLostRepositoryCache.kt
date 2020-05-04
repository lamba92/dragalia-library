package com.github.lamba92.dragalialost.data.repositories

import com.github.lamba92.dragalialost.data.utils.MongoDBInitializer
import com.github.lamba92.dragalialost.domain.entities.*
import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepositoryCache
import com.mongodb.MongoWriteException
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import java.time.Instant

class MongoDBDragaliaLostRepositoryCache private constructor(db: CoroutineDatabase) : DragaliaLostRepositoryCache {

    companion object : MongoDBInitializer<MongoDBDragaliaLostRepositoryCache> {
        override suspend fun initCollections(db: CoroutineDatabase) {
            listOf("adventurerEntities", "dragonEntities", "wyrmprintEntities")
                .filter { it !in db.listCollectionNames() }
                .forEach { db.createCollection(it) }
        }

        override suspend fun initialize(db: CoroutineDatabase): MongoDBDragaliaLostRepositoryCache {
            initCollections(db)
            return MongoDBDragaliaLostRepositoryCache(db)
        }

        override fun initializeBlocking(db: CoroutineDatabase) =
            runBlocking {
                initialize(db)
            }
    }

    @Serializable
    private data class AdventurerDocument(
        val _id: String,
        val data: AdventurerEntity,
        val creationTime: Long = System.currentTimeMillis()
    )

    @Serializable
    private data class DragonDocument(
        val _id: String,
        val data: DragonEntity,
        val creationTime: Long = System.currentTimeMillis()
    )

    @Serializable
    private data class WyrmprintDocument(
        val _id: String,
        val data: WyrmprintEntity,
        val creationTime: Long = System.currentTimeMillis()
    )

    private val adventurersCollection =
        db.getCollection<AdventurerDocument>("adventurerEntities")

    private val dragonsCollection =
        db.getCollection<DragonDocument>("dragonEntities")

    private val wyrmprintsCollection =
        db.getCollection<WyrmprintDocument>("wyrmprintEntities")

    override suspend fun getAdventurerById(id: DragaliaId) =
        adventurersCollection.findOneById(id.toString())?.data

    override suspend fun getDragonById(id: DragaliaId) =
        dragonsCollection.findOneById(id.toString())?.data

    override suspend fun getWyrmprintById(id: DragaliaId) =
        wyrmprintsCollection.findOneById(id.toString())?.data

    override suspend fun cache(data: DragaliaEntity): Boolean {
        when (data) {
            is AdventurerEntity -> adventurersCollection.save(AdventurerDocument(data.id.toString(), data))
            is DragonEntity -> dragonsCollection.save(DragonDocument(data.id.toString(), data))
            is WyrmprintEntity -> wyrmprintsCollection.save(WyrmprintDocument(data.id.toString(), data))
        }
        return true
    }

    override suspend fun invalidateCache() {
        listOf(adventurersCollection, dragonsCollection, wyrmprintsCollection)
            .forEach { it.deleteMany() }
    }

}
