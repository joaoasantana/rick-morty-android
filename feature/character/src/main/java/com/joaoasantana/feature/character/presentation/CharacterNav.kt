package com.joaoasantana.feature.character.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.joaoasantana.core.common.navigation.NavGraph
import com.joaoasantana.core.common.navigation.NavRouter
import com.joaoasantana.feature.character.presentation.ui.list.CharacterListScreen

fun NavGraphBuilder.characterNavGraph() {
    navigation<NavGraph.CharacterGraph>(startDestination = NavRouter.CharacterList) {
        composable<NavRouter.CharacterList> { CharacterListScreen() }
    }
}