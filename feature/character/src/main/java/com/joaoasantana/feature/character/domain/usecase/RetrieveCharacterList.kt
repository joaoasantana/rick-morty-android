package com.joaoasantana.feature.character.domain.usecase

import com.joaoasantana.core.common.Response
import com.joaoasantana.feature.character.domain.entity.Character
import com.joaoasantana.feature.character.domain.repository.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

fun interface RetrieveCharacterList {
    operator fun invoke(): Flow<Response<List<Character>>>
}

class RetrieveCharacterListImpl(
    private val characterRepository: CharacterRepository,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : RetrieveCharacterList {

    override operator fun invoke(): Flow<Response<List<Character>>> {
        return characterRepository.getCharacterList().flowOn(coroutineDispatcher)
    }
}
