package com.github.lamba92.dragalia.di.modules

import com.github.lamba92.dragalia.di.KodeinModuleProvider
import com.github.lamba92.dragalia.di.expects.platformSpecificRepositoriesBindings
import com.github.lamba92.dragalia.repositories.DragaliaLostRepository
import com.github.lamba92.dragalia.repositories.DragaliaLostRepositoryImplementation
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

object RepositoriesModule : KodeinModuleProvider {
    override fun provideModule(): Kodein.Builder.() -> Unit = {
        bind<DragaliaLostRepository>() with singleton {
            DragaliaLostRepositoryImplementation(
                instance(), instance(), instance(),
                instance(), instance(), instance(), instance()
            )
        }
        platformSpecificRepositoriesBindings()
    }
}
