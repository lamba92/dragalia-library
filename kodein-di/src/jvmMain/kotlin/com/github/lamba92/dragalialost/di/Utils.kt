package com.github.lamba92.dragalialost.di

import com.github.lamba92.dragalialost.core.datasource.MongoDBGamepediaCache
import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasourceCache
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun dragaliaMongoDBCacheModule(host: String, port: Int, dbName: String) =
    Kodein.Module("Dragalia Lost Cache Module") {
        bind<CoroutineDatabase>() with singleton {
            KMongo.createClient("mongodb://$host:$port").coroutine.getDatabase(dbName)
        }
        bind<GamepediaDatasourceCache>() with singleton {
            MongoDBGamepediaCache.initializeBlocking(instance())
        }
    }

fun dragaliaMongoDBCacheModule(db: CoroutineDatabase) =
    Kodein.Module("Dragalia Lost Cache Module") {
        bind<CoroutineDatabase>() with singleton { db }
        bind<GamepediaDatasourceCache>() with singleton {
            MongoDBGamepediaCache.initializeBlocking(instance())
        }
    }
