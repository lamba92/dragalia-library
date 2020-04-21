package com.github.lamba92.dragalialost.di.modules

import com.github.lamba92.dragalialost.di.DITags.*
import com.github.lamba92.dragalialost.di.KodeinModuleProvider
import io.ktor.http.URLProtocol.Companion.HTTPS
import org.kodein.di.Kodein
import org.kodein.di.erased.with

object ConstantsModule : KodeinModuleProvider {
    override fun provideModule(): Kodein.Builder.() -> Unit = {
        constant(DRAGALIA_GAMEPEDIA_URL_PROTOCOL) with HTTPS
        constant(DRAGALIA_GAMEPEDIA_URL) with "dragalialost.gamepedia.com"
        constant(DRAGALIA_GAMEPEDIA_URL_API_PATH) with "api.php"
    }
}
