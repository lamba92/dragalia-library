package com.github.lamba92.dragalialost.domain.tests

import com.github.lamba92.dragalialost.domain.repositories.DragaliaLostRepository
import com.github.lamba92.dragalialost.domain.repositories.ext.searchAdventurers
import com.github.lamba92.dragalialost.domain.repositories.ext.searchDragons
import com.github.lamba92.dragalialost.domain.repositories.ext.searchWyrmprints
import com.github.lamba92.dragalialost.domain.tests.mock.MockDragaliaLostRepository
import com.github.lamba92.dragalialost.domain.tests.mock.MockEntities
import kotlin.test.Test
import kotlin.test.assertEquals

class RepositoryTests {

    private val repo = MockDragaliaLostRepository() as DragaliaLostRepository

    @Test
    fun getAdventurerTest() = runTest {
        assertEquals(listOf(MockEntities.adventurer), repo.searchAdventurers {})
    }

    @Test
    fun getDragonTest() = runTest {
        assertEquals(listOf(MockEntities.dragon), repo.searchDragons {})
    }

    @Test
    fun getWyrmprintTest() = runTest {
        assertEquals(listOf(MockEntities.wyrmprint), repo.searchWyrmprints {})
    }

}