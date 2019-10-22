package com.github.lamba92.dragalialost.kodeindi.modules

import com.github.lamba92.dragalialost.kodeindi.KodeinModuleProvider
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton

object NetworkModule : KodeinModuleProvider {
    override fun provideModule(): Kodein.Builder.() -> Unit = {
        bind<HttpClient>() with singleton {
            HttpClient {
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
                install(Logging) {
                    level = LogLevel.ALL
                }
            }
        }
    }
}