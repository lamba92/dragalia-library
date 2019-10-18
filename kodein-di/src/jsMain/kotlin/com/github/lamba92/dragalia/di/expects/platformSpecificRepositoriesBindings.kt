package com.github.lamba92.dragalia.di.expects

import com.github.lamba92.dragalia.js.repositories.DragaliaLostJsRepository
import com.github.lamba92.dragalialost.js.repository.DragaliaLostJsRepositoryImplementation
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

actual fun Kodein.Builder.platformSpecificRepositoriesBindings() {
    bind<DragaliaLostJsRepository>() with singleton { DragaliaLostJsRepositoryImplementation(instance()) }
}