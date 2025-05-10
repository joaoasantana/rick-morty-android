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

class RetrieveCharacterListImplTest {

    @MockK
    private lateinit var characterRepository: CharacterRepository

    private lateinit var retrieveCharacterList: RetrieveCharacterList

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        retrieveCharacterList = RetrieveCharacterListImpl(
            characterRepository = characterRepository,
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should return success response when repository returns success`() = runBlocking {
        val expected = Response.Success<List<Character>>(mockk())

        every { characterRepository.getCharacterList() } returns flowOf(expected)

        val response = retrieveCharacterList().first()

        Assert.assertEquals(expected, response)

        verify { characterRepository.getCharacterList() }
    }

    @Test
    fun `should return failure response when repository returns failure`() = runBlocking {
        val expected = Response.Failure<List<Character>>(mockk())

        every { characterRepository.getCharacterList() } returns flowOf(expected)

        val response = retrieveCharacterList().first()

        Assert.assertEquals(expected, response)

        verify { characterRepository.getCharacterList() }
    }
}
