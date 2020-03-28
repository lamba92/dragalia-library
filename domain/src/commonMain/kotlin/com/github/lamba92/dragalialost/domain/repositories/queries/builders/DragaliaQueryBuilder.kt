@file:Suppress("MemberVisibilityCanBePrivate")

package com.github.lamba92.dragalialost.domain.repositories.queries.builders

import com.github.lamba92.dragalialost.domain.entities.enums.AbilityType
import com.github.lamba92.dragalialost.domain.entities.enums.Rarity
import com.github.lamba92.dragalialost.domain.repositories.queries.DragaliaQuery


abstract class DragaliaQueryBuilder<T : DragaliaQuery> {

    var name: String? = null

    protected val rarities = mutableSetOf<Rarity>()
    protected val abilityTypes = mutableSetOf<AbilityType>()

    fun addRarity(rarity: Rarity) {
        rarities.add(rarity)
    }

    fun addAbilityType(abilityType: AbilityType) {
        abilityTypes.add(abilityType)
    }

    fun anyRarity() =
        Rarity.values().forEach { addRarity(it) }

    fun anyAbilityType() =
        AbilityType.values().forEach { addAbilityType(it) }

    abstract fun build(): T

}
