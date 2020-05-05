package com.github.lamba92.dragalialost.di

import com.github.lamba92.dragalialost.di.modules.*
import io.ktor.client.features.logging.LogLevel
import org.kodein.di.Kodein
import org.kodein.di.erased.with

fun dragaliaLostModule(isDebug: Boolean = false, httpLogLevel: LogLevel = LogLevel.BODY) =
    Kodein.Module("Dragalia Lost Module") {
        import(DatasourcesModule)
        import(MappersModule)
        import(NetworkModule)
        import(RepositoriesModule)
        import(UseCasesModule)
        import(ConstantsModule)
        constant(DITags.IS_DEBUG) with isDebug
        if (isDebug) {
            constant(DITags.HTTP_LOG_LEVEL) with httpLogLevel
        }
    }
