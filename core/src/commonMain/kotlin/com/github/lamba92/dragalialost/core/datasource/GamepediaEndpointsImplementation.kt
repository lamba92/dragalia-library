package com.github.lamba92.dragalialost.core.datasource

import com.github.lamba92.dragalialost.core.datasource.DatasourceTables.*
import com.github.lamba92.dragalialost.core.utils.buildCargoWhereClause
import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasource
import com.github.lamba92.dragalialost.data.rawresponses.*
import com.github.lamba92.dragalialost.data.utils.Utils.cargoIdPropertyOf
import com.github.lamba92.dragalialost.data.utils.Utils.cargoPropertiesOf
import io.ktor.http.URLProtocol
import io.ktor.http.Url
import kotlin.js.JsName

class GamepediaEndpointsImplementation(
    override val protocol: URLProtocol,
    override val host: String,
    override val port: Int = protocol.defaultPort,
    override val path: String
) : GamepediaDatasource.Endpoints {

    private fun parametersOf(vararg headers: Pair<String, Any>) =
        io.ktor.http.parametersOf(*headers.map { it.first to listOf(it.second.toString()) }.toTypedArray())

    @JsName("buildUrlBase1")
    private fun buildUrlBase(
        table: DatasourceTables,
        limit: Int,
        fields: List<String>,
        builder: CargoQueryWhereClauseBuilder.() -> Unit
    ) = Url(
        protocol, host, port, path, parametersOf(
            "action" to "cargoquery",
            "format" to "json",
            "limit" to limit,
            "tables" to table,
            "fields" to fields.joinToString(","),
            "where" to buildCargoWhereClause(builder)
        ),
        "", null, null, false
    )

    @JsName("buildUrlBase2")
    private fun buildUrlBase(
        table: DatasourceTables,
        limit: Int,
        field: String,
        builder: CargoQueryWhereClauseBuilder.() -> Unit
    ) = buildUrlBase(table, limit, listOf(field), builder)

    private inline fun <reified T : CargoQueryable> buildAllFieldsByIdUrl(
        table: DatasourceTables,
        id: String
    ) = buildUrlBase(table, 1, cargoPropertiesOf<T>()) {
        appendEquality(cargoIdPropertyOf<T>(), id)
    }

    @JsName("buildIdUrl1")
    private inline fun <reified T : CargoQueryable> buildIdUrl(
        table: DatasourceTables,
        limit: Int,
        noinline builder: CargoQueryWhereClauseBuilder.() -> Unit
    ) = buildUrlBase(table, limit, cargoIdPropertyOf<T>(), builder)

    @JsName("buildIdUrl2")
    private inline fun <reified T : CargoQueryable> buildIdUrl(
        table: DatasourceTables,
        name: String?,
        limit: Int,
        noinline builder: CargoQueryWhereClauseBuilder.() -> Unit = {}
    ) = buildIdUrl<T>(table, limit) {
        name?.let { appendLike("name", it) }
        builder()
    }

    @JsName("buildIdUrl3")
    private inline fun <reified T : CargoQueryable> buildIdUrl(
        table: DatasourceTables,
        name: String?,
        element: String?,
        rarity: Int?,
        limit: Int,
        noinline builder: CargoQueryWhereClauseBuilder.() -> Unit = {}
    ) = buildIdUrl<T>(table, name, limit) {
        element?.let { appendEquality("element", it) }
        rarity?.let { appendEquality("rarity", it) }
        builder()
    }

    override fun searchAdventurerIdsUrl(
        name: String?,
        weaponType: String?,
        element: String?,
        heroClass: String?,
        rarity: Int?,
        limit: Int
    ) = buildIdUrl<AdventurerJSON>(ADVENTURERS_TABLE, name, element, rarity, limit) {
        weaponType?.let { appendEquality("weaponType", it) }
        heroClass?.let { appendEquality("heroClass", it) }
    }

    override fun searchDragonIdsUrl(name: String?, element: String?, rarity: Int?, limit: Int) =
        buildIdUrl<DragonJSON>(DRAGONS_TABLE, name, element, rarity, limit)

    override fun searchWyrmprintIdsUrl(name: String?, element: String?, rarity: Int?, limit: Int) =
        buildIdUrl<WyrmprintJSON>(WYRMPRINTS_TABLE, name, element, rarity, limit)

    override fun searchWeaponIdsUrl(name: String?, element: String?, rarity: Int?, limit: Int) =
        buildIdUrl<WeaponJSON>(WEAPONS_TABLE, name, element, rarity, limit)

    override fun searchAbilityIdsUrl(name: String?, limit: Int) =
        buildIdUrl<AbilityJSON>(ABILITIES_TABLE, name, limit)

    override fun searchCoAbilityIdsUrl(name: String?, limit: Int) =
        buildIdUrl<CoAbilityJSON>(CO_ABILITIES_TABLE, name, limit)

    override fun searchSkillIdsUrl(name: String?, limit: Int) =
        buildIdUrl<SkillJSON>(SKILLS_TABLE, name, limit)

    override fun searchAbilityLimitedGroupIdsUrl(
        name: String?,
        isEffectMIx: Boolean?,
        maxLimitedValue: Int?,
        abilityLimitedText: String?,
        limit: Int
    ) = buildIdUrl<AbilityLimitedGroupJSON>(ABILITY_LIMITED_GROUPS_TABLE, name, limit) {
        isEffectMIx?.let { appendEquality("isEffectMIx", it) }
        maxLimitedValue?.let { appendEquality("maxLimitedValue", it) }
        abilityLimitedText?.let { appendEquality("abilityLimitedText", it) }
    }

    override fun getAdventurerByIdUrl(id: String) =
        buildAllFieldsByIdUrl<AdventurerJSON>(ADVENTURERS_TABLE, id)

    override fun getDragonByIdUrl(id: String) =
        buildAllFieldsByIdUrl<DragonJSON>(DRAGONS_TABLE, id)

    override fun getWyrmprintByIdUrl(id: String) =
        buildAllFieldsByIdUrl<WyrmprintJSON>(WYRMPRINTS_TABLE, id)

    override fun getWeaponByIdUrl(id: String) =
        buildAllFieldsByIdUrl<WeaponJSON>(WEAPONS_TABLE, id)

    override fun getAbilityByIdUrl(id: String) =
        buildAllFieldsByIdUrl<AbilityJSON>(ABILITIES_TABLE, id)

    override fun getCoAbilityByIdUrl(id: String) =
        buildAllFieldsByIdUrl<CoAbilityJSON>(CO_ABILITIES_TABLE, id)

    override fun getSkillByIdUrl(id: String) =
        buildAllFieldsByIdUrl<SkillJSON>(SKILLS_TABLE, id)

    override fun getAbilityLimitedGroupByIdUrl(id: String) =
        buildAllFieldsByIdUrl<AbilityLimitedGroupJSON>(ABILITY_LIMITED_GROUPS_TABLE, id)

}