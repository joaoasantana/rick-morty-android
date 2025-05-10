package com.joaoasantana.feature.character.data.repository

import com.joaoasantana.feature.character.data.source.CharacterService
import com.joaoasantana.feature.character.data.source.CharacterStorage
import com.joaoasantana.feature.character.domain.repository.CharacterRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Test

class CharacterRepositoryImplTest {

    @MockK
    private lateinit var characterService: CharacterService

    @MockK
    private lateinit var characterStorage: CharacterStorage

    private lateinit var characterRepository: CharacterRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        characterRepository = CharacterRepositoryImpl(
            characterService = characterService,
            characterStorage = characterStorage
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}
