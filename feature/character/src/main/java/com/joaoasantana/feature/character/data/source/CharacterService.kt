package com.joaoasantana.feature.character.data.source

import com.joaoasantana.core.common.ResponseService
import com.joaoasantana.feature.character.data.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("character")
    suspend fun fetchCharacters(
        @Query("page") page: Int,
    ): ResponseService<List<CharacterResponse>>
}
