package com.github.lamba92.dragalialost.di

import com.github.lamba92.dragalialost.core.datasource.MongoDBGamepediaCache
import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasourceCache
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun dragaliaMongoDBCacheModule(host: String = "localhost", port: Int = 27017, dbName: String = "db") =
    Kodein.Module("Dragalia Lost Cache Module") {
        bind<CoroutineClient>() with singleton {
            KMongo.createClient("mongodb://$host:$port").coroutine
        }
        bind<CoroutineDatabase>() with singleton {
            instance<CoroutineClient>().getDatabase(dbName)
        }
        bind<GamepediaDatasourceCache>() with singleton {
            MongoDBGamepediaCache.initializeBlocking(instance(), instance())
        }
    }

fun dragaliaMongoDBCacheModule(db: CoroutineDatabase, client: CoroutineClient) =
    Kodein.Module("Dragalia Lost Cache Module") {
        bind<CoroutineDatabase>() with singleton { db }
        bind<CoroutineClient>() with singleton { client }
        bind<GamepediaDatasourceCache>() with singleton {
            MongoDBGamepediaCache.initializeBlocking(instance(), instance())
        }
    }
