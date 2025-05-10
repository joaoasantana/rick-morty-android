package com.joaoasantana.rickandmorty.core

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavHost(
    navHostController: NavHostController = rememberNavController(),
) {
    NavHost(navHostController, startDestination = "dummy") {
        composable("dummy") { DummyScreen() }
    }
}

@Composable
fun DummyScreen() {
    Text(text = "Hello World!!")
}
