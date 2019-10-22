package com.github.lamba92.dragalialost.kodeindi.modules

import com.github.lamba92.dragalialost.core.datasource.GamepediaDatasourceImplementation
import com.github.lamba92.dragalialost.core.datasource.GamepediaEndpointsImplementation
import com.github.lamba92.dragalialost.core.datasource.InMemoryGamepediaCache
import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasource
import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasourceCache
import com.github.lamba92.dragalialost.kodeindi.DITags.*
import com.github.lamba92.dragalialost.kodeindi.KodeinModuleProvider
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

object DatasourcesModule : KodeinModuleProvider {

    override fun provideModule(): Kodein.Builder.() -> Unit = {
        bind<GamepediaDatasource.Endpoints>() with singleton {
            GamepediaEndpointsImplementation(
                instance(DRAGALIA_GAMEPEDIA_URL_PROTOCOL),
                instance(DRAGALIA_GAMEPEDIA_URL),
                instance(DRAGALIA_GAMEPEDIA_URL_API_PATH)
            )
        }
        bind<GamepediaDatasource>() with singleton { GamepediaDatasourceImplementation(instance(), instance()) }
        bind<GamepediaDatasourceCache>() with singleton { InMemoryGamepediaCache() }
    }
}