package com.github.lamba92.dragalialost.domain.utils

interface Cache {
    suspend fun invalidateCache()
}
