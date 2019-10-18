package com.github.lamba92.dragalialost.core.utils

fun buildCargoWhereClause(builder: com.github.lamba92.dragalialost.core.datasource.CargoQueryWhereClauseBuilder.() -> Unit) =
    com.github.lamba92.dragalialost.core.datasource.CargoQueryWhereClauseBuilder().apply(builder).toString()