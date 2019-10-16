package com.github.lamba92.dragalia.datasource

import com.github.lamba92.dragalia.rawresponses.*
import com.github.lamba92.dragalia.utils.Utils.cargoPropertiesOf
import com.github.lamba92.dragalia.utils.buildWhereClause
import io.ktor.http.URLProtocol
import io.ktor.http.Url

class GamepediaEndpointsImplementation(
    override val protocol: URLProtocol,
    override val host: String,
    override val port: Int = protocol.defaultPort
) : GamepediaDatasource.Endpoints {

    private fun parametersOf(vararg headers: Pair<String, Any>) =
        io.ktor.http.parametersOf(*headers.map { it.first to listOf(it.second.toString()) }.toTypedArray())

    private inline fun <reified T : CargoQueryable> buildUrl1(
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
            "fields" to cargoPropertiesOf<T>().joinToString(","),
            "where" to buildWhereClause(builder)
        ), "", null, null, false
    )

    private inline fun <reified T : CargoQueryable> buildUrl2(
        table: String,
        name: String?,
        limit: Int,
        path: String = "api.php",
        noinline builder: CargoQueryWhereClauseBuilder.() -> Unit = {}
    ) = buildUrl1<T>(table, limit, path) {
        name?.let { appendLike("name", it) }
        builder()
    }

    private inline fun <reified T : CargoQueryable> buildUrl3(
        table: String,
        name: String?,
        element: String?,
        rarity: Int?,
        limit: Int,
        path: String = "api.php",
        noinline builder: CargoQueryWhereClauseBuilder.() -> Unit = {}
    ) = buildUrl2<T>(table, name, limit, path) {
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
    ) = buildUrl3<AdventurerJSON>("Adventurers", name, element, rarity, limit) {
        weaponType?.let { appendEquality("weaponType", it) }
        heroClass?.let { appendEquality("heroClass", it) }
    }

    override fun searchDragonsUrl(name: String?, element: String?, rarity: Int?, limit: Int) =
        buildUrl3<DragonJSON>("Dragons", name, element, rarity, limit)

    override fun searchWyrmprintsUrl(name: String?, element: String?, rarity: Int?, limit: Int) =
        buildUrl3<WyrmprintJSON>("Wyrmprints", name, element, rarity, limit)

    override fun searchAbilitiesUrl(name: String?, limit: Int) =
        buildUrl2<AbilityJSON>("Abilities", name, limit)

    override fun searchWeaponsUrl(name: String?, element: String?, rarity: Int?, limit: Int) =
        buildUrl3<WeaponsJSON>("Weapons", name, element, rarity, limit)

    override fun searchCoAbilitiesUrl(name: String?, limit: Int) =
        buildUrl2<CoAbilityJSON>("CoAbilities", name, limit)

    override fun searchSkillsUrl(name: String?, limit: Int) =
        buildUrl2<SkillJSON>("Skills", name, limit)

    override fun searchAbilityLimitedGroupsUrl(
        name: String?,
        isEffectMIx: Boolean?,
        maxLimitedValue: Int?,
        abilityLimitedText: String?,
        limit: Int
    ) = buildUrl2<AbilityLimitedGroupJSON>("AbilityLimitedGroup", name, limit) {
        isEffectMIx?.let { appendEquality("isEffectMIx", it) }
        maxLimitedValue?.let { appendEquality("maxLimitedValue", it) }
        abilityLimitedText?.let { appendEquality("abilityLimitedText", it) }
    }
}