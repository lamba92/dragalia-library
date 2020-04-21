package com.github.lamba92.dragalialost.di.modules

import com.github.lamba92.dragalialost.di.DITags
import com.github.lamba92.dragalialost.di.KodeinModuleProvider
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logging
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

object NetworkModule : KodeinModuleProvider {
    override fun provideModule(): Kodein.Builder.() -> Unit = {
        bind<HttpClient>() with singleton {
            HttpClient {
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
                if (instance(DITags.IS_DEBUG))
                    install(Logging) {
                        level = instance(DITags.HTTP_LOG_LEVEL)
                    }
            }
        }
    }
}
