package com.joaoasantana.rickandmorty.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joaoasantana.feature.character.presentation.ui.list.CharacterListScreen

@Composable
fun MainNavHost(
    navHostController: NavHostController = rememberNavController(),
) {
    NavHost(navHostController, startDestination = "character_list") {
        composable("character_list") { CharacterListScreen() }
    }
}
