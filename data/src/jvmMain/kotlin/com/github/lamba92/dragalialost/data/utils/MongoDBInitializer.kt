package com.github.lamba92.dragalialost.data.utils

import org.litote.kmongo.coroutine.CoroutineDatabase

interface MongoDBInitializer<T> {
    suspend fun initCollections(db: CoroutineDatabase)
    suspend fun initialize(db: CoroutineDatabase): T
    fun initializeBlocking(db: CoroutineDatabase): T
}
