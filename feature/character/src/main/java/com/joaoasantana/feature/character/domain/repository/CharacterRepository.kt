package com.joaoasantana.feature.character.domain.repository

import com.joaoasantana.core.common.Response
import com.joaoasantana.feature.character.domain.entity.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacterList(page: Int): Flow<Response<List<Character>>>
}
