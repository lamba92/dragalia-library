package com.github.lamba92.dragalia.tests

import com.github.lamba92.dragalia.datasource.GamepediaDatasource
import com.github.lamba92.dragalia.datasource.GamepediaDatasourceImplementation
import com.github.lamba92.dragalia.datasource.GamepediaEndpointsImplementation
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.http.URLProtocol.Companion.HTTPS
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class Test {

    @Test
    fun t(): Unit = runBlocking {
        val endpoints =
            GamepediaEndpointsImplementation(HTTPS, "dragalialost.gamepedia.com") as GamepediaDatasource.Endpoints

        val httpClient = HttpClient(Apache) {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }

        val ds = GamepediaDatasourceImplementation(httpClient, endpoints) as GamepediaDatasource

        listOf(
            ds.searchAbilities(),
            ds.searchAdventurers(),
            ds.searchAbilityLimitedGroups(),
            ds.searchCoAbilities(),
            ds.searchDragons(),
            ds.searchSkills(),
            ds.searchWeapons(),
            ds.searchWyrmprints()
        ).forEach {
            print("$it\n")
        }
    }

}