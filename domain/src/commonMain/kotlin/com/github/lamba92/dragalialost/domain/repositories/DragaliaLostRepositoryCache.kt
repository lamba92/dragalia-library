package com.github.lamba92.dragalialost.domain.repositories

import com.github.lamba92.dragalialost.domain.entities.*
import com.github.lamba92.dragalialost.domain.utils.Cache

interface DragaliaLostRepositoryCache : Cache {

    suspend fun getAdventurerById(id: DragaliaId): AdventurerEntity?
    suspend fun getDragonById(id: DragaliaId): DragonEntity?
    suspend fun getWyrmprintById(id: DragaliaId): WyrmprintEntity?

    suspend fun cache(data: DragaliaEntity): Boolean

}
