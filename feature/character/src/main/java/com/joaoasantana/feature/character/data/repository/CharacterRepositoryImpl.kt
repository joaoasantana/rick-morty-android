package com.joaoasantana.feature.character.data.repository

import com.joaoasantana.core.common.Response
import com.joaoasantana.feature.character.data.mapper.toDomain
import com.joaoasantana.feature.character.data.mapper.toEntity
import com.joaoasantana.feature.character.data.model.CharacterEntity
import com.joaoasantana.feature.character.data.source.CharacterService
import com.joaoasantana.feature.character.data.source.CharacterStorage
import com.joaoasantana.feature.character.domain.entity.Character
import com.joaoasantana.feature.character.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepositoryImpl(
    private val characterService: CharacterService,
    private val characterStorage: CharacterStorage,
) : CharacterRepository {

    var page: Int = 0

    override fun getCharacter(id: Int): Flow<Response<Character>> {
        return flow {
            runCatching {
                characterStorage.findById(id)
            }
                .onSuccess { emit(Response.Success(it.toDomain())) }
                .onFailure { emit(Response.Failure(it)) }
        }
    }

    override fun getCharacterList(): Flow<Response<List<Character>>> {
        return flow {
            runCatching {
                characterStorage.findByPage(page)
                    .takeIf { it.isNotEmpty() } ?: updateCharacters()
            }
                .onSuccess { emit(Response.Success(it.map { it.toDomain() })) }
                .onFailure { emit(Response.Failure(it)) }
        }
    }

    private suspend fun updateCharacters(): List<CharacterEntity> {
        return characterService.fetchCharacters(page).results
            .map { it.toEntity(page) }
            .also { characterStorage.insert(it) }
    }
}
