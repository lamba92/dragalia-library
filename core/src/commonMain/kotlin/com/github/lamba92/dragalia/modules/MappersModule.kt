package com.github.lamba92.dragalia.modules

import com.github.lamba92.dragalia.mappers.AdventurerMapper
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton

object MappersModule : KodeinModuleProvider {
    override fun provideModule(): Kodein.Builder.() -> Unit = {
        bind<AdventurerMapper>() with singleton { AdventurerMapper() }
    }
}