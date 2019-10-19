package com.github.lamba92.dragalialost.core.utils

import com.github.lamba92.dragalialost.core.datasource.CargoQueryWhereClauseBuilder

fun buildCargoWhereClause(builder: CargoQueryWhereClauseBuilder.() -> Unit) =
    CargoQueryWhereClauseBuilder().apply(builder).toString()