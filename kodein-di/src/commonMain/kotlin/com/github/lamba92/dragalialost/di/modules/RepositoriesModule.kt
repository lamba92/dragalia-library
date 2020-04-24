package com.github.lamba92.dragalialost.di.modules

import com.github.lamba92.dragalialost.data.repositories.DragaliaLostRepositoryImplementation
import com.github.lamba92.dragalialost.di.KodeinModuleProvider
import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.instanceOrNull
import org.kodein.di.erased.singleton

object RepositoriesModule : KodeinModuleProvider {
    override fun provideModule(): Kodein.Builder.() -> Unit = {
        bind<DragaliaLostRepository>() with singleton {
            DragaliaLostRepositoryImplementation(
                instance(), instanceOrNull(), instanceOrNull(), instance(),
                instance(), instance(), instance(), instance(), instance()
            )
        }
    }
}
