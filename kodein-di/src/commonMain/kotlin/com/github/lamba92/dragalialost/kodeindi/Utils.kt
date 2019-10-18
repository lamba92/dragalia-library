package com.github.lamba92.dragalialost.kodeindi

import com.github.lamba92.dragalialost.kodeindi.modules.DatasourcesModule
import com.github.lamba92.dragalialost.kodeindi.modules.MappersModule
import com.github.lamba92.dragalialost.kodeindi.modules.NetworkModule
import com.github.lamba92.dragalialost.kodeindi.modules.RepositoriesModule
import org.kodein.di.Kodein

fun dragaliaLostModule() = Kodein.Module("Dragalia Lost Module") {
    import(DatasourcesModule)
    import(MappersModule)
    import(NetworkModule)
    import(RepositoriesModule)
}
