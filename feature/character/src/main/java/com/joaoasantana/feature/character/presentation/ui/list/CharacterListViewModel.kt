package com.joaoasantana.feature.character.presentation.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaoasantana.core.common.Response
import com.joaoasantana.feature.character.domain.usecase.RetrieveCharacterList
import com.joaoasantana.feature.character.presentation.mapper.toModel
import com.joaoasantana.feature.character.presentation.model.CharacterModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

sealed interface CharacterListState {
    data class Loaded(val characters: List<CharacterModel>) : CharacterListState
    data object Loading : CharacterListState
    data class Failure(val errorMessage: String) : CharacterListState
}

class CharacterListViewModel(
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val retrieveCharacterList: RetrieveCharacterList,
) : ViewModel() {

    private var page: Int = 1

    private val _state: MutableStateFlow<CharacterListState> =
        MutableStateFlow(CharacterListState.Loading)
    val state: StateFlow<CharacterListState> = _state.asStateFlow()

    init {
        viewModelScope.launch(coroutineDispatcher) {
            fetchCharacterList()
        }
    }

    suspend fun fetchCharacterList() {
        _state.emit(CharacterListState.Loading)

        retrieveCharacterList.invoke(page).collectLatest { response ->
            when (response) {
                is Response.Success -> _state.emit(
                    CharacterListState.Loaded(response.data.map { it.toModel() }),
                ).also { page++ }

                is Response.Failure -> _state.emit(
                    CharacterListState.Failure(response.error.message.orEmpty()),
                )
            }
        }
    }
}
