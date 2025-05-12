package com.joaoasantana.feature.character.presentation.ui.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joaoasantana.feature.character.presentation.component.CharacterImage
import com.joaoasantana.feature.character.presentation.component.CharacterResource
import com.joaoasantana.feature.character.presentation.mapper.toModel
import com.joaoasantana.feature.character.presentation.model.CharacterModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterListScreen(
    listState: LazyListState = rememberLazyListState(),
    viewModel: CharacterListViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val modelList = remember { mutableStateListOf<CharacterModel>() }

    val reachedBottom: Boolean by remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem?.index != 0 && lastVisibleItem?.index == listState.layoutInfo.totalItemsCount - 1
        }
    }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom) viewModel.fetchCharacterList()
    }

    LaunchedEffect(state.value) {
        val currentState = state.value

        if (currentState is CharacterListState.Loaded) {
            modelList.addAll(currentState.characters.map { it.toModel() })
        }
    }

    LazyColumn(
        Modifier
            .padding(16.dp)
            .fillMaxSize(),
        listState,
    ) {
        items(modelList.size, { modelList[it] }) { index ->
            Card(
                elevation = CardDefaults.cardElevation(2.dp),
                shape = RoundedCornerShape(corner = CornerSize(12.dp)),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
            ) {
                Row {
                    CharacterImage(modelList[index])
                    CharacterResource(modelList[index])
                }
            }
        }
    }
}
