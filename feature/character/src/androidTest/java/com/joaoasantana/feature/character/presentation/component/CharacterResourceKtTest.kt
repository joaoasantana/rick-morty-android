package com.joaoasantana.feature.character.presentation.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.joaoasantana.feature.character.presentation.model.CharacterModel
import org.junit.Rule
import org.junit.Test

class CharacterResourceKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun shouldShowCharacterResource() {
        val model = characterModel()

        composeTestRule.setContent {
            CharacterResource(model)
        }

        composeTestRule.onNodeWithText(model.name).assertIsDisplayed()
    }

    private fun characterModel() = CharacterModel(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        type = "Scient",
        gender = "",
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    )
}
