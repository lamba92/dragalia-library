package com.github.lamba92.dragalialost.core.datasource

class CargoQueryWhereClauseBuilder : Appendable, CharSequence {

    private val clauses = arrayListOf<String>()

    fun appendLike(field: String, query: Any) =
        clauses.add("$field LIKE \"%$query%\"").let { this }

    fun appendEquality(field: String, query: Any) =
        clauses.add("$field = \"$query\"").let { this }

    override fun append(value: Char) =
        clauses.add(value.toString()).let { this }

    override fun append(value: CharSequence?) =
        clauses.add(value.toString()).let { this }

    override fun append(value: CharSequence?, startIndex: Int, endIndex: Int) =
        value.toString().substring(startIndex, endIndex).let { append(it) }.let { this }

    override val length =
        toString().length

    override fun get(index: Int) =
        toString()[index]

    override fun subSequence(startIndex: Int, endIndex: Int) =
        toString().subSequence(startIndex, endIndex)

    override fun toString() =
        clauses.joinToString(" AND ")

}
