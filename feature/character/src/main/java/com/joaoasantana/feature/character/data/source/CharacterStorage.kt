package com.joaoasantana.feature.character.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.joaoasantana.feature.character.data.model.CharacterEntity

@Dao
interface CharacterStorage {

    @Insert
    suspend fun insert(vararg characters: CharacterEntity)

    @Query("SELECT * FROM character")
    suspend fun findByPage(page: Int): List<CharacterEntity>
}
