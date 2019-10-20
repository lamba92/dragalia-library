package com.github.lamba92.dragalialost.domain.tests

import com.github.lamba92.dragalialost.core.datasource.GamepediaEndpointsImplementation
import com.github.lamba92.dragalialost.data.datasource.GamepediaDatasource
import io.ktor.http.URLProtocol
import kotlin.test.Test

class Tests {

    @Test
    fun t1() {
        val e = GamepediaEndpointsImplementation(
            URLProtocol.HTTPS,
            "dragalialost.gamepedia.com",
            "api.php"
        ) as GamepediaDatasource.Endpoints

        println(e.searchAdventurerIdsUrl())

    }

}