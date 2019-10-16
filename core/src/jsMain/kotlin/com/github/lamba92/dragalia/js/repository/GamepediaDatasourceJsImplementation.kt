package com.github.lamba92.dragalia.js.repository

import com.github.lamba92.dragalia.datasource.GamepediaDatasource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise

class GamepediaDatasourceJsImplementation(originalDatasource: GamepediaDatasource) :
    GamepediaDatasource by originalDatasource {

    fun searchAdventurersAsPromise(
        name: String? = null,
        weaponType: String? = null,
        element: String? = null,
        heroClass: String? = null,
        rarity: Int? = null,
        limit: Int = 10
    ) = GlobalScope.promise { searchAdventurers(name, weaponType, element, heroClass, rarity, limit) }

    fun l() {
    }

}