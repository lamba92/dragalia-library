package com.github.lamba92.dragalialost.core.datasource

class CargoQueryWhereClauseBuilder : Appendable, CharSequence {

    private val clauses = arrayListOf<String>()

    fun appendLike(field: String, query: Any) =
        clauses.add("$field LIKE \"$query\"").let { this }

    fun appendEquality(field: String, query: Any) =
        clauses.add("$field=\"$query\"").let { this }

    override fun append(c: Char) =
        clauses.add(c.toString()).let { this }

    override fun append(csq: CharSequence?) =
        clauses.add(csq.toString()).let { this }

    override fun append(csq: CharSequence?, start: Int, end: Int) =
        csq.toString().substring(start, end).let { append(it) }.let { this }

    override val length =
        toString().length

    override fun get(index: Int) =
        toString()[index]

    override fun subSequence(startIndex: Int, endIndex: Int) =
        toString().subSequence(startIndex, endIndex)

    override fun toString() =
        clauses.joinToString(" AND ")

}