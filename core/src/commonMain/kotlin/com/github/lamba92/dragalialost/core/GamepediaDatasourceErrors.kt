package com.github.lamba92.dragalialost.core

import com.github.lamba92.dragalialost.data.DragaliaError
import io.ktor.http.Url

sealed class GamepediaDatasourceErrors : DragaliaError() {

    abstract val url: Url

    class AbilityNotFoundException(
        val id: String,
        override val url: Url,
        override val cause: Throwable,
        override val message: String = "Unable to find ability on Gamepedia with id $id | url: $url"
    ) : GamepediaDatasourceErrors()

}
