package com.github.lamba92.dragalialost.kodeindi

import com.github.lamba92.dragalialost.kodeindi.modules.*
import org.kodein.di.Kodein

fun dragaliaLostModule() = Kodein.Module("Dragalia Lost Module") {
    import(DatasourcesModule)
    import(MappersModule)
    import(NetworkModule)
    import(RepositoriesModule)
    import(ConstantsModule)
}
