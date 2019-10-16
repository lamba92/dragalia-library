package com.github.lamba92.dragalia.utils

import com.github.lamba92.dragalia.datasource.CargoQueryWhereClauseBuilder

fun buildWhereClause(builder: CargoQueryWhereClauseBuilder.() -> Unit) =
    CargoQueryWhereClauseBuilder().apply(builder).toString()