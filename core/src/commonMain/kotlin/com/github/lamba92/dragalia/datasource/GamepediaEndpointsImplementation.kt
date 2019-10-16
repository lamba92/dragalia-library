package com.github.lamba92.dragalia.datasource

import com.github.lamba92.dragalia.rawresponses.AdventurerJSON
import com.github.lamba92.dragalia.rawresponses.CargoQueryable
import com.github.lamba92.dragalia.rawresponses.DragonJSON
import com.github.lamba92.dragalia.rawresponses.WyrmprintJSON
import com.github.lamba92.dragalia.utils.buildWhereClause
import com.github.lamba92.dragalia.utils.propertiesOf
import io.ktor.http.URLProtocol
import io.ktor.http.Url

class GamepediaEndpointsImplementation(
    override val protocol: URLProtocol,
    override val host: String,
    override val port: Int = protocol.defaultPort
) : GamepediaDatasource.Endpoints {

    private fun parametersOf(vararg headers: Pair<String, Any>) =
        io.ktor.http.parametersOf(*headers.map { it.first to listOf(it.second.toString()) }.toTypedArray())

    private inline fun <reified T : CargoQueryable> buildUrl(
        table: String,
        limit: Int = 500,
        path: String = "api.php",
        noinline builder: CargoQueryWhereClauseBuilder.() -> Unit
    ) = Url(
        protocol, host, port, path, parametersOf(
            "action" to "cargoquery",
            "format" to "json",
            "limit" to limit,
            "tables" to table,
            "fields" to propertiesOf<T>().joinToString(","),
            "where" to buildWhereClause(builder)
        ), "", null, null, false
    )

    private inline fun <reified T : CargoQueryable> buildUrl(
        table: String,
        name: String?,
        element: String?,
        rarity: Int?,
        limit: Int,
        path: String = "api.php",
        noinline builder: CargoQueryWhereClauseBuilder.() -> Unit = {}
    ) = buildUrl<AdventurerJSON>(table, limit, path) {
        name?.let { appendLike("name", it) }
        element?.let { appendEquality("element", it) }
        rarity?.let { appendEquality("rarity", it) }
        builder()
    }

    override fun searchAdventurersUrl(
        name: String?,
        weaponType: String?,
        element: String?,
        heroClass: String?,
        rarity: Int?,
        limit: Int
    ) = buildUrl<AdventurerJSON>("Adventurers", name, element, rarity, limit) {
        weaponType?.let { appendEquality("weaponType", it) }
        heroClass?.let { appendEquality("heroClass", it) }
    }

    override fun searchDragonsUrl(name: String?, element: String?, rarity: Int?, limit: Int) =
        buildUrl<DragonJSON>("Dragons", name, element, rarity, limit)

    override fun searchWyrmprintsUrl(name: String?, element: String?, rarity: Int?, limit: Int) =
        buildUrl<WyrmprintJSON>("Wyrmprints", name, element, rarity, limit)

}