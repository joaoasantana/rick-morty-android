package com.joaoasantana.feature.character.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import com.joaoasantana.feature.character.presentation.model.CharacterModel

@Composable
fun CharacterImage(character: CharacterModel) {
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(character.image)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    AsyncImage(
        model = imageRequest,
        contentDescription = character.name,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(corner = CornerSize(12.dp)))
    )
}
