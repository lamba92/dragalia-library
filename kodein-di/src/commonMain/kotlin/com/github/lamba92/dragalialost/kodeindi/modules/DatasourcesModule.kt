package com.github.lamba92.dragalialost.kodeindi.modules

import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasource
import com.github.lamba92.dragalialost.kodeindi.KodeinModuleProvider
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

object DatasourcesModule : KodeinModuleProvider {

    override fun provideModule(): Kodein.Builder.() -> Unit = {
        bind<GamepediaDatasource.Endpoints>() with singleton {
            com.github.lamba92.dragalialost.core.datasource.GamepediaEndpointsImplementation(
                instance(),
                instance("dragalia_gamepedia_url")
            )
        }
        bind<GamepediaDatasource>() with singleton {
            com.github.lamba92.dragalialost.core.datasource.GamepediaDatasourceImplementation(
                instance(),
                instance()
            )
        }
    }
}