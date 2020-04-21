package com.github.lamba92.dragalialost.data.datasource

interface Cache {
    suspend fun invalidateCache()
}
