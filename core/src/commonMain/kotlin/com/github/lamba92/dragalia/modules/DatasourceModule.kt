package com.github.lamba92.dragalia.modules

import com.github.lamba92.dragalia.datasource.GamepediaDatasource
import com.github.lamba92.dragalia.datasource.GamepediaDatasourceImplementation
import com.github.lamba92.dragalia.datasource.GamepediaEndpointsImplementation
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

object DatasourceModule : KodeinModuleProvider {
    override fun provideModule(): Kodein.Builder.() -> Unit = {
        bind<GamepediaDatasource.Endpoints>() with singleton {
            GamepediaEndpointsImplementation(
                instance(),
                instance("dragalia_gamepedia_url")
            )
        }
        bind<GamepediaDatasource>() with singleton { GamepediaDatasourceImplementation(instance(), instance()) }
    }
}