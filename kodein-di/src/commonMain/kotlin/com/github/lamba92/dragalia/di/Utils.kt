package com.github.lamba92.dragalia.di

import com.github.lamba92.dragalia.di.modules.DatasourcesModule
import com.github.lamba92.dragalia.di.modules.MappersModule
import com.github.lamba92.dragalia.di.modules.NetworkModule
import com.github.lamba92.dragalia.di.modules.RepositoriesModule
import org.kodein.di.Kodein

fun dragaliaLostModule() = Kodein.Module("Dragalia Lost Module") {
    import(DatasourcesModule)
    import(MappersModule)
    import(NetworkModule)
    import(RepositoriesModule)
}
