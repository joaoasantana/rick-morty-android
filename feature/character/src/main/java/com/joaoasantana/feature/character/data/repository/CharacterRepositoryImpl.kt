package com.joaoasantana.feature.character.data.repository

import com.joaoasantana.core.common.Response
import com.joaoasantana.feature.character.data.mapper.toDomain
import com.joaoasantana.feature.character.data.mapper.toEntity
import com.joaoasantana.feature.character.data.source.CharacterService
import com.joaoasantana.feature.character.data.source.CharacterStorage
import com.joaoasantana.feature.character.domain.entity.Character
import com.joaoasantana.feature.character.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class CharacterRepositoryImpl(
    private val characterService: CharacterService,
    private val characterStorage: CharacterStorage,
) : CharacterRepository {

    override fun getCharacterList(page: Int): Flow<Response<List<Character>>> =
        flow { emit(localCharacterList(page)) }
            .catch { emit(remoteCharacterList(page)) }
            .catch { emit(Response.Failure(it)) }

    private suspend fun localCharacterList(page: Int): Response<List<Character>> {
        return characterStorage.findByPage(page)
            .also { require(it.isNotEmpty()) }
            .let { Response.Success(it.map { it.toDomain() }) }
    }

    private suspend fun remoteCharacterList(page: Int): Response<List<Character>> {
        return characterService.fetchCharacters(page).results
            .map { it.toEntity(page) }
            .also { characterStorage.insert(it) }
            .let { Response.Success(it.map { it.toDomain() }) }
    }
}
