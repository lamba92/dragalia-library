package com.github.lamba92.dragalia.utils

import com.github.lamba92.dragalia.datasource.CargoQueryWhereClauseBuilder

fun buildCargoWhereClause(builder: CargoQueryWhereClauseBuilder.() -> Unit) =
    CargoQueryWhereClauseBuilder().apply(builder).toString()