package com.github.lamba92.dragalialost.di

import com.github.lamba92.dragalialost.core.datasource.MongoDBGamepediaCache
import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasourceCache
import com.github.lamba92.dragalialost.data.repositories.MongoDBDragaliaLostRepositoryCache
import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepositoryCache
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import java.util.logging.Logger

fun dragaliaMongoDBCacheModule(host: String = "localhost", port: Int = 27017, dbName: String = "dragalia") =
    Kodein.Module("Dragalia Lost Cache Module") {
        bind<CoroutineClient>() with singleton {
            KMongo.createClient("mongodb://$host:$port").coroutine
        }
        bind<CoroutineDatabase>() with singleton {
            instance<CoroutineClient>().getDatabase(dbName)
        }
        commonMongoStuff()
    }

fun dragaliaMongoDBCacheModule(db: CoroutineDatabase, client: CoroutineClient) =
    Kodein.Module("Dragalia Lost Cache Module") {
        bind<CoroutineDatabase>() with singleton { db }
        bind<CoroutineClient>() with singleton { client }
        commonMongoStuff()
    }

private fun Kodein.Builder.commonMongoStuff() {
    bind<GamepediaDatasourceCache>() with singleton {
        MongoDBGamepediaCache.initializeBlocking(instance())
    }
    bind<DragaliaLostRepositoryCache>() with singleton {
        MongoDBDragaliaLostRepositoryCache.initializeBlocking(instance())
    }
}
