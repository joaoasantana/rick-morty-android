package com.joaoasantana.feature.character.domain.usecase

import com.joaoasantana.core.common.Response
import com.joaoasantana.feature.character.domain.entity.Character
import com.joaoasantana.feature.character.domain.repository.CharacterRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RetrieveCharacterImplTest {

    @MockK
    private lateinit var characterRepository: CharacterRepository

    private lateinit var retrieveCharacter: RetrieveCharacter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        retrieveCharacter = RetrieveCharacterImpl(
            characterRepository = characterRepository,
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should return success response when repository returns success`() = runBlocking {
        val id = 1
        val expected = Response.Success<Character>(mockk())

        every { characterRepository.getCharacter(any()) } returns flowOf(expected)

        val response = retrieveCharacter(id).first()

        Assert.assertEquals(expected, response)

        verify { characterRepository.getCharacter(id) }
    }

    @Test
    fun `should return failure response when repository returns failure`() = runBlocking {
        val id = 1
        val expected = Response.Failure<Character>(mockk())

        every { characterRepository.getCharacter(any()) } returns flowOf(expected)

        val response = retrieveCharacter(id).first()

        Assert.assertEquals(expected, response)

        verify { characterRepository.getCharacter(id) }
    }
}
