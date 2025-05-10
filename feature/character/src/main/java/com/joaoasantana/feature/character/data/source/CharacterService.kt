package com.joaoasantana.feature.character.data.source

import com.joaoasantana.feature.character.data.model.CharacterResponse
import com.joaoasantana.feature.character.data.model.CharacterResponseList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    @GET("character/{id}")
    suspend fun fetchCharacter(@Path("id") id: Int): CharacterResponse

    @GET("character")
    suspend fun fetchCharacterList(@Query("page") page: Int): CharacterResponseList
}
