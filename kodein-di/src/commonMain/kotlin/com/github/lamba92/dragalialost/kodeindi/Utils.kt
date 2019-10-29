package com.github.lamba92.dragalialost.kodeindi

import com.github.lamba92.dragalialost.kodeindi.modules.*
import io.ktor.client.features.logging.LogLevel
import org.kodein.di.DKodein
import org.kodein.di.Kodein
import org.kodein.di.erased.instance
import org.kodein.di.erased.with

fun dragaliaLostModule(isDebug: Boolean = false, httpLogLevel: LogLevel = LogLevel.BODY) =
    Kodein.Module("Dragalia Lost Module") {
    import(DatasourcesModule)
    import(MappersModule)
    import(NetworkModule)
    import(RepositoriesModule)
    import(UseCasesModule)
    import(ConstantsModule)
        constant("isDebug") with isDebug
        if (isDebug) constant("httpLogLevel") with httpLogLevel
    }

internal val DKodein.isDebug
    get() = instance<Boolean>("isDebug")