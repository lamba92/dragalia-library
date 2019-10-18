package com.github.lamba92.dragalialost.kodeindi.expects

import com.github.lamba92.dragalialost.data.js.repository.DragaliaLostJsRepositoryImplementation
import com.github.lamba92.dragalialost.domain.js.repositories.DragaliaLostJsRepository
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

actual fun Kodein.Builder.platformSpecificRepositoriesBindings() {
    bind<DragaliaLostJsRepository>() with singleton { DragaliaLostJsRepositoryImplementation(instance()) }
}