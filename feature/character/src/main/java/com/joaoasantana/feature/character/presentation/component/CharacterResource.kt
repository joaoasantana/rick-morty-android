package com.joaoasantana.feature.character.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joaoasantana.feature.character.presentation.model.CharacterModel

@Composable
fun CharacterResource(character: CharacterModel) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(character.name, style = MaterialTheme.typography.bodyMedium)
    }
}
