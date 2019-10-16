package com.github.lamba92.dragalia

import com.github.lamba92.dragalia.datasource.GamepediaDatasource
import com.github.lamba92.dragalia.datasource.GamepediaDatasourceImplementation
import com.github.lamba92.dragalia.datasource.GamepediaEndpointsImplementation
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.http.URLProtocol

suspend fun main() {
    val endpoints = GamepediaEndpointsImplementation(URLProtocol.HTTPS, "dragalialost.gamepedia.com")

    val httpClient = HttpClient(Apache) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        install(Logging) {
            level = LogLevel.ALL
        }
    }

    val ds = GamepediaDatasourceImplementation(httpClient, endpoints) as GamepediaDatasource

    ds.searchDragons(limit = 10)
        .let { println(it.cargoquery[0].title) }

    ds.searchAdventurers(limit = 10)
        .let { println(it.cargoquery[0].title) }

    ds.searchWyrmprints(limit = 10)
        .let { println(it.cargoquery[0].title) }

    ds.searchAbility(limit = 10)
        .let { println(it.cargoquery[0].title) }

}