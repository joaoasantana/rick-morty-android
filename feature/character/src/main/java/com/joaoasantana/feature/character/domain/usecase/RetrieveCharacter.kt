package com.joaoasantana.feature.character.domain.usecase

import com.joaoasantana.core.common.Response
import com.joaoasantana.feature.character.domain.entity.Character
import com.joaoasantana.feature.character.domain.repository.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

fun interface RetrieveCharacter {
    operator fun invoke(id: Int): Flow<Response<Character>>
}

class RetrieveCharacterImpl(
    private val characterRepository: CharacterRepository,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : RetrieveCharacter {

    override operator fun invoke(id: Int): Flow<Response<Character>> {
        return characterRepository.getCharacter(id).flowOn(coroutineDispatcher)
    }
}
