package com.github.lamba92.dragalialost.kodeindi.tests

import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.kodeindi.dragaliaLostModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.instance

class AdventurerTests : KodeinAware {

    override val kodein by Kodein.lazy {
        import(dragaliaLostModule())
    }

    private val repository by instance<DragaliaLostRepository>()


}
