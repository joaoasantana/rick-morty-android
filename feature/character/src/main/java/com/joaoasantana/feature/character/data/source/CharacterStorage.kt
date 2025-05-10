package com.joaoasantana.feature.character.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joaoasantana.feature.character.data.model.CharacterEntity

@Dao
interface CharacterStorage {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: List<CharacterEntity>)

    @Query("SELECT * FROM character WHERE id = :id LIMIT 1")
    suspend fun findById(id: Int): CharacterEntity

    @Query("SELECT * FROM character WHERE page = :page")
    suspend fun findByPage(page: Int): List<CharacterEntity>
}
