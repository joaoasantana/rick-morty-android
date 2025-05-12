package com.joaoasantana.feature.character.data.repository

import com.joaoasantana.core.common.Response
import com.joaoasantana.core.common.ResponseService
import com.joaoasantana.feature.character.data.model.CharacterEntity
import com.joaoasantana.feature.character.data.model.CharacterResponse
import com.joaoasantana.feature.character.data.source.CharacterService
import com.joaoasantana.feature.character.data.source.CharacterStorage
import com.joaoasantana.feature.character.domain.entity.Character
import com.joaoasantana.feature.character.domain.repository.CharacterRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
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

    @Test
    fun `should return success response when local storage returns success`() =
        runBlocking {
            val page = 1
            val expected = Response.Success<List<Character>>(character())

            coEvery { characterStorage.findByPage(any()) } returns characterEntity()

            val response = characterRepository.getCharacterList(page).first()

            Assert.assertEquals(expected, response)

            coVerify { characterStorage.findByPage(page) }
        }

    @Test
    fun `should return success response when remote storage returns success`() =
        runBlocking {
            val page = 1
            val expected = Response.Success<List<Character>>(character())

            coEvery { characterStorage.findByPage(any()) } returns emptyList()
            coEvery { characterService.fetchCharacters(any()) } returns characterResponse()
            coEvery { characterStorage.insert(any()) } returns Unit

            val response = characterRepository.getCharacterList(page).first()

            Assert.assertEquals(expected, response)

            coVerify { characterStorage.findByPage(page) }
            coVerify { characterService.fetchCharacters(page) }
            coVerify { characterStorage.insert(characterEntity()) }
        }

    @Test
    fun `should return failure response when local and remote calls returns failure`() =
        runBlocking {
            val page = 1
            val error = mockk<Exception>()
            val expected = Response.Failure<List<Character>>(error)

            coEvery { characterStorage.findByPage(any()) } throws error
            coEvery { characterService.fetchCharacters(any()) } throws error

            val response = characterRepository.getCharacterList(page).first()

            Assert.assertEquals(expected, response)

            coVerify { characterStorage.findByPage(page) }
            coVerify { characterService.fetchCharacters(page) }
        }

    private fun character() = listOf(
        Character(
            id = 1,
            name = "Character Name",
            status = "Alive",
            species = "",
            type = "",
            gender = "",
            image = "",
        )
    )

    private fun characterEntity() = listOf(
        CharacterEntity(
            id = 1,
            page = 1,
            name = "Character Name",
            status = "Alive",
            species = "",
            type = "",
            gender = "",
            image = "",
        )
    )

    private fun characterResponse() = ResponseService(
        results = listOf(
            CharacterResponse(
                id = 1,
                name = "Character Name",
                status = "Alive",
                species = "",
                type = "",
                gender = "",
                image = "",
            )
        )
    )
}
