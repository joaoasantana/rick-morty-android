package com.joaoasantana.feature.character.presentation.ui.list

import com.joaoasantana.core.common.Response
import com.joaoasantana.feature.character.domain.entity.Character
import com.joaoasantana.feature.character.domain.usecase.RetrieveCharacterList
import com.joaoasantana.feature.character.presentation.model.CharacterModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CharacterListViewModelTest {

    @MockK
    private lateinit var retrieveCharacterList: RetrieveCharacterList

    private lateinit var characterListViewModel: CharacterListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        characterListViewModel = CharacterListViewModel(
            retrieveCharacterList = retrieveCharacterList
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should send Loaded state when usecase returns success`() = runBlocking {
        val response = Response.Success<List<Character>>(character())

        val expected = CharacterListState.Loaded(characterModel())

        every { retrieveCharacterList(any()) } returns flowOf(response)

        characterListViewModel.fetchCharacterList()

        Assert.assertEquals(expected, characterListViewModel.state.first())

        verify { retrieveCharacterList(1) }
    }

    @Test
    fun `should send Failure state when usecase returns failure`() = runBlocking {
        val error = Exception("test")
        val response = Response.Failure<List<Character>>(error)

        val expected = CharacterListState.Failure(error.message.orEmpty())

        every { retrieveCharacterList(any()) } returns flowOf(response)

        characterListViewModel.fetchCharacterList()

        Assert.assertEquals(expected, characterListViewModel.state.first())

        verify { retrieveCharacterList(1) }
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

    private fun characterModel() = listOf(
        CharacterModel(
            id = 1,
            name = "Character Name",
            status = "Alive",
            species = "",
            type = "",
            gender = "",
            image = "",
        )
    )
}
