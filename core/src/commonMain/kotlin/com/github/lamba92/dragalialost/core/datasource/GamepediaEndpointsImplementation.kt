package com.github.lamba92.dragalialost.core.datasource

import com.github.lamba92.dragalialost.core.datasource.DatasourceTables.*
import com.github.lamba92.dragalialost.core.utils.buildCargoWhereClause
import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasource
import com.github.lamba92.dragalialost.data.rawresponses.*
import com.github.lamba92.dragalialost.data.utils.CargoProperties
import com.github.lamba92.dragalialost.data.utils.addIfNotWith
import io.ktor.http.URLProtocol
import io.ktor.http.Url
import kotlin.js.JsName

class GamepediaEndpointsImplementation(
    override val protocol: URLProtocol,
    override val host: String,
    override val path: String,
    override val port: Int = protocol.defaultPort
) : GamepediaDatasource.Endpoints {

    private fun parametersOf(vararg parameters: Pair<String, Any>) =
        io.ktor.http.parametersOf(*parameters.map { it.first to listOf(it.second.toString()) }.toTypedArray())

    private fun buildBaseUrl(
        action: String,
        vararg parameters: Pair<String, Any>,
        format: String = "json"
    ) = Url(
        protocol, host, port, path, parametersOf(
            "action" to action,
            "format" to format,
            *parameters
        ), "", null, null, false
    )

    private fun buildFileUrl(fileName: String) =
        buildBaseUrl(
            "query",
            "prop" to "imageinfo",
            "titles" to fileName.addIfNotWith("File:", ".png"),
            "iiprop" to "url"
        )

    @JsName("buildBaseCargoQueryUrl1")
    private fun buildBaseCargoQueryUrl(
        table: DatasourceTables,
        limit: Int,
        fields: List<String>,
        builder: CargoQueryWhereClauseBuilder.() -> Unit
    ) = buildBaseUrl(
        "cargoquery",
        "limit" to limit,
        "tables" to table,
        "fields" to fields.joinToString(","),
        "where" to buildCargoWhereClause(builder)
    )

    private inline fun <reified T : CargoQueryable> buildAllFieldsUrl(
        table: DatasourceTables,
        limit: Int = 1,
        noinline builder: CargoQueryWhereClauseBuilder.() -> Unit
    ) = buildBaseCargoQueryUrl(table, limit, CargoProperties.of<T>(), builder)

    @JsName("buildIdUrl1")
    private inline fun <reified T : CargoQueryable> buildIdUrl(
        table: DatasourceTables,
        limit: Int,
        noinline builder: CargoQueryWhereClauseBuilder.() -> Unit
    ) = buildBaseCargoQueryUrl(table, limit, CargoProperties.idOf<T>(), builder)

    @JsName("buildIdUrl2")
    private inline fun <reified T : CargoQueryable> buildIdUrl(
        table: DatasourceTables,
        name: String?,
        limit: Int,
        noinline builder: CargoQueryWhereClauseBuilder.() -> Unit = {}
    ) = buildIdUrl<T>(table, limit) {
        name?.let { appendLike(CargoProperties.nameOf<T>(), it) }
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
        element?.let { appendEquality("ElementalType", it) }
        rarity?.let { appendEquality("Rarity ", it) }
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
        heroClass?.let { appendEquality("CharaType", it) }
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

    override fun getAdventurerByIdsUrl(id: String, variationId: String) =
        buildAllFieldsUrl<AdventurerJSON>(ADVENTURERS_TABLE) {
            appendEquality("Id", id)
            appendEquality("VariationId", variationId)
        }

    override fun getDragonByIdUrl(id: String) =
        buildAllFieldsUrl<DragonJSON>(DRAGONS_TABLE) {
            appendEquality("Id", id)
        }

    override fun getWyrmprintByIdUrl(id: String) =
        buildAllFieldsUrl<WyrmprintJSON>(WYRMPRINTS_TABLE) {
            appendEquality("Id", id)
        }

    override fun getWeaponByIdUrl(id: String) =
        buildAllFieldsUrl<WeaponJSON>(WEAPONS_TABLE) {
            appendEquality("Id", id)
        }

    override fun getAbilityByIdUrl(id: String) =
        buildAllFieldsUrl<AbilityJSON>(ABILITIES_TABLE) {
            appendEquality("Id", id)
        }

    override fun getCoAbilityByIdUrl(id: String) =
        buildAllFieldsUrl<CoAbilityJSON>(CO_ABILITIES_TABLE) {
            appendEquality("Id", id)
        }

    override fun getSkillByNameUrl(name: String) =
        buildAllFieldsUrl<SkillJSON>(SKILLS_TABLE) {
            appendEquality("Name", name)
        }

    override fun getAbilityLimitedGroupByIdUrl(id: String) =
        buildAllFieldsUrl<AbilityLimitedGroupJSON>(ABILITY_LIMITED_GROUPS_TABLE) {
            appendEquality("Id", id)
        }

    override fun getAbilityGroupByGroupIdUrl(groupId: String) =
        buildAllFieldsUrl<AbilityGroupJSON>(ABILITY_GROUPS_TABLE) {
            appendEquality("Id", groupId)
        }

    override fun getAdventurerIconByIdUrl(id: String, variationId: String, rarity: Int) =
        buildFileUrl("${id}_0${variationId}_r0$rarity")

    override fun getAdventurerPortraitByIdUrl(id: String, variationId: String, rarity: Int) =
        buildFileUrl("${id}_0${variationId}_r0${rarity}_portrait")

    override fun getAbilityIconByFileNameUrl(fileName: String) =
        buildFileUrl(fileName)

    override fun getCoAbilityIconByFileNameUrl(fileName: String) =
        getAbilityIconByFileNameUrl(fileName)

    override fun getDragonIconByIdUrl(id: String) =
        buildFileUrl("${id}_01")

    override fun getDragonPortraitByIdUrl(id: String) =
        buildFileUrl("${id}_01_portrait")

    override fun getWyrmprintIconByIdsUrl(id: String, vestige: Int) =
        buildFileUrl("${id}_0$vestige")

    override fun getWyrmprintPortraitByIdsUrl(id: String, vestige: Int) =
        buildFileUrl("${id}_0${vestige}_portrait")

    override fun getSkillIconByIconNameUrl(fileName: String) =
        buildFileUrl(fileName)

}
