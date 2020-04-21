package com.github.lamba92.dragalialost.di.modules

import com.github.lamba92.dragalialost.di.KodeinModuleProvider
import com.github.lamba92.dragalialost.domain.usecases.SearchAllByNameUseCase
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

object UseCasesModule : KodeinModuleProvider {
    override fun provideModule(): Kodein.Builder.() -> Unit = {
        bind<SearchAllByNameUseCase>() with singleton { SearchAllByNameUseCase(instance()) }
    }
}
