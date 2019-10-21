package com.github.lamba92.dragalialost.kodeindi.modules

import com.github.lamba92.dragalialost.data.repositories.DragaliaLostRepositoryImplementation
import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.kodeindi.KodeinModuleProvider
import com.github.lamba92.dragalialost.kodeindi.expects.platformSpecificRepositoriesBindings
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

object RepositoriesModule : KodeinModuleProvider {
    override fun provideModule(): Kodein.Builder.() -> Unit = {
        bind<DragaliaLostRepository>() with singleton {
            DragaliaLostRepositoryImplementation(
                instance(), instance(), instance(), instance(),
                instance(), instance(), instance(), instance()
            )
        }
        platformSpecificRepositoriesBindings()
    }
}
