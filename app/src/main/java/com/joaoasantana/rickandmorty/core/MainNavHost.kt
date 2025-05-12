package com.joaoasantana.rickandmorty.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.joaoasantana.core.common.navigation.NavGraph
import com.joaoasantana.feature.character.presentation.characterNavGraph

@Composable
fun MainNavHost(
    navHostController: NavHostController = rememberNavController(),
) {
    NavHost(navHostController, startDestination = NavGraph.CharacterGraph) {
        characterNavGraph()
    }
}
