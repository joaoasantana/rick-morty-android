package com.joaoasantana.feature.character.presentation.ui.list

import androidx.lifecycle.ViewModel
import com.joaoasantana.core.common.Response
import com.joaoasantana.feature.character.domain.usecase.RetrieveCharacterList
import com.joaoasantana.feature.character.presentation.mapper.toModel
import com.joaoasantana.feature.character.presentation.model.CharacterModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed interface CharacterListState {
    data class Loaded(val characters: List<CharacterModel>) : CharacterListState
    data object Loading : CharacterListState
    data class Failure(val errorMessage: String) : CharacterListState
}

class CharacterListViewModel(
    private val retrieveCharacterList: RetrieveCharacterList
) : ViewModel() {

    private var page: Int = 1

    private val _state: MutableStateFlow<CharacterListState> =
        MutableStateFlow(CharacterListState.Loading)
    val state: StateFlow<CharacterListState> = _state.asStateFlow()

    suspend fun fetchCharacterList() {
        _state.emit(CharacterListState.Loading)

        retrieveCharacterList.invoke(page++).collect { response ->
            when (response) {
                is Response.Success -> _state.emit(
                    CharacterListState.Loaded(response.data.map { it.toModel() }),
                )

                is Response.Failure -> _state.emit(
                    CharacterListState.Failure(response.error.message.orEmpty()),
                )
            }
        }
    }
}
