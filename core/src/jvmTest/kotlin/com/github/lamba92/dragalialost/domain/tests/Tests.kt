package com.github.lamba92.dragalialost.domain.tests

import com.github.lamba92.dragalialost.core.utils.buildCargoWhereClause
import kotlin.test.Test

class Tests {

    @Test
    fun t1() {
        println(buildCargoWhereClause {
            appendLike("name", "euden")
            appendEquality("element", "light")
            appendEquality("weaponType", "sword")
            appendEquality("rarity", 5)
        })
    }

}