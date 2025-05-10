package com.joaoasantana.rickandmorty.core

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
fun MainContent() {
    MaterialTheme {
        Surface {
            MainNavHost()
        }
    }
}
