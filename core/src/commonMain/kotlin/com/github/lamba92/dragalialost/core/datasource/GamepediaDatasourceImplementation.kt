package com.github.lamba92.dragalialost.core.datasource

import com.github.lamba92.dragalialost.data.DragaliaError
import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasource
import com.github.lamba92.dragalialost.data.datasource.queries.*
import com.github.lamba92.dragalialost.data.rawresponses.*
import com.github.lamba92.dragalialost.data.utils.asDragaliaId
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class GamepediaDatasourceImplementation(
    private val httpClient: HttpClient,
    private val endpoints: GamepediaDatasource.Endpoints
) : GamepediaDatasource {

    // SEARCHES
    override suspend fun searchAdventurerIds(
        query: AdventurersCargoQuery,
        limit: Int
    ) = with(query) {
        httpClient.get<AdventurerIdCargoJSON>(
            endpoints.searchAdventurerIdsUrl(name, weaponType, element, heroClass, rarity, limit)
        ).cargoquery.map { it.title }
    }

    override suspend fun searchDragonIds(query: DragonsCargoQuery, limit: Int) = with(query) {
        httpClient.get<IdCargoJSON>(endpoints.searchDragonIdsUrl(name, element, rarity, limit))
            .cargoquery.map { it.title.Id }
    }

    override suspend fun searchWyrmprintIds(query: WyrmprintsCargoQuery, limit: Int) = with(query) {
        httpClient.get<IdCargoJSON>(endpoints.searchWyrmprintIdsUrl(name, rarity, limit))
            .cargoquery.map { it.title.Id }
    }

    override suspend fun searchWeaponIds(query: WeaponsCargoQuery, limit: Int) = with(query) {
        httpClient.get<IdCargoJSON>(endpoints.searchWeaponIdsUrl(name, element, rarity, limit))
            .cargoquery.map { it.title.Id }
    }

    override suspend fun searchAbilityIds(query: AbilitiesCargoQuery, limit: Int) = with(query) {
        httpClient.get<IdCargoJSON>(endpoints.searchAbilityIdsUrl(name, limit))
            .cargoquery.map { it.title.Id }
    }

    override suspend fun searchCoAbilityIds(query: CoAbilitiesCargoQuery, limit: Int) = with(query) {
        httpClient.get<IdCargoJSON>(endpoints.searchCoAbilityIdsUrl(name, limit))
            .cargoquery.map { it.title.Id }
    }

    override suspend fun searchSkillIds(query: SkillsCargoQuery, limit: Int) = with(query) {
        httpClient.get<SkillIdCargoJSON>(endpoints.searchSkillIdsUrl(name, limit))
            .cargoquery.map { it.title.SkillId }
    }

    override suspend fun searchAbilityLimitedGroupIds(query: AbilityLimitedGroupsCargoQuery, limit: Int) = with(query) {
        httpClient.get<IdCargoJSON>(
            endpoints.searchAbilityLimitedGroupIdsUrl(name, isEffectMIx, maxLimitedValue, abilityLimitedText, limit)
        ).cargoquery.map { it.title.Id }
    }

    // SINGLE ITEMS BY ID
    override suspend fun getAdventurerByIds(id: String, variationId: String) =
        httpClient.get<AdventurerCargoJSON>(endpoints.getAdventurerByIdsUrl(id, variationId))
            .cargoquery.first().title

    override suspend fun getDragonById(id: String) =
        httpClient.get<DragonCargoJSON>(endpoints.getDragonByIdUrl(id)).cargoquery.first().title

    override suspend fun getWyrmprintById(id: String) =
        httpClient.get<WyrmprintCargoJSON>(endpoints.getWyrmprintByIdUrl(id))
            .cargoquery.first().title

    override suspend fun getWeaponsById(id: String) =
        httpClient.get<WeaponCargoJSON>(endpoints.getWeaponByIdUrl(id)).cargoquery.first().title

    override suspend fun getAbilityById(id: String) = try {
        httpClient.get<AbilityCargoJSON>(endpoints.getAbilityByIdUrl(id)).cargoquery.first().title
    } catch (e: Throwable) {
        throw DragaliaError.AbilityNotFoundException(asDragaliaId(id), e)
    }

    override suspend fun getCoAbilityById(id: String) =
        httpClient.get<CoAbilityCargoJSON>(endpoints.getCoAbilityByIdUrl(id)).cargoquery.first().title

    override suspend fun getSkillById(id: String) =
        httpClient.get<SkillCargoJSON>(endpoints.getSkillByIdUrl(id)).cargoquery.first().title

    override suspend fun getSkillByName(name: String) =
        httpClient.get<SkillCargoJSON>(endpoints.getSkillByNameUrl(name)).cargoquery.first().title

    override suspend fun getAbilityLimitedGroupsById(id: String) =
        httpClient.get<AbilityLimitedGroupCargoJSON>(endpoints.getAbilityLimitedGroupByIdUrl(id)).cargoquery.first().title

    override suspend fun getAbilityGroupsByGroupId(groupId: String) =
        httpClient.get<AbilityGroupCargoJSON>(endpoints.getAbilityGroupByGroupIdUrl(groupId)).cargoquery.single().title

    // IMAGES
    override suspend fun getAdventurerIconById(id: String, variationId: String, rarity: Int) =
        httpClient.get<FileQueryJSON>(endpoints.getAdventurerIconByIdUrl(id, variationId, rarity))
            .query.pages.values.single().imageinfo.firstOrNull()

    override suspend fun getAdventurerPortraitById(id: String, variationId: String, rarity: Int) =
        httpClient.get<FileQueryJSON>(endpoints.getAdventurerPortraitByIdUrl(id, variationId, rarity))
            .query.pages.values.single().imageinfo.firstOrNull()

    override suspend fun getDragonIconById(id: String) =
        httpClient.get<FileQueryJSON>(endpoints.getDragonIconByIdUrl(id))
            .query.pages.values.single().imageinfo.firstOrNull()

    override suspend fun getDragonPortraitById(id: String) =
        httpClient.get<FileQueryJSON>(endpoints.getDragonPortraitByIdUrl(id))
            .query.pages.values.single().imageinfo.firstOrNull()

    override suspend fun getWyrmprintIconByIds(id: String, vestige: Int) =
        httpClient.get<FileQueryJSON>(endpoints.getWyrmprintIconByIdsUrl(id, vestige))
            .query.pages.values.single().imageinfo.firstOrNull()

    override suspend fun getWyrmprintPortraitByIds(id: String, vestige: Int) =
        httpClient.get<FileQueryJSON>(endpoints.getWyrmprintPortraitByIdsUrl(id, vestige))
            .query.pages.values.single().imageinfo.firstOrNull()

    override suspend fun getAbilityIconByFileName(fileName: String) =
        httpClient.get<FileQueryJSON>(endpoints.getAbilityIconByFileNameUrl(fileName))
            .query.pages.values.single().imageinfo.firstOrNull()

    override suspend fun getCoAbilityIconByFileName(fileName: String) =
        getAbilityIconByFileName(fileName)

    override suspend fun getSkillIconByIconName(fileName: String) =
        httpClient.get<FileQueryJSON>(endpoints.getSkillIconByIconNameUrl(fileName))
            .query.pages.values.single().imageinfo.firstOrNull()

}
