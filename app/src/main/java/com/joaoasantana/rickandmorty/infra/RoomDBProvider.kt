package com.joaoasantana.rickandmorty.infra

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.joaoasantana.feature.character.data.model.CharacterEntity
import com.joaoasantana.feature.character.data.source.CharacterStorage

@Database(
    entities = [CharacterEntity::class],
    version = 2,
    exportSchema = false,
)
abstract class RoomDBProvider : RoomDatabase() {

    abstract fun characterDao(): CharacterStorage

    companion object {
        const val NAME = "rick_morty"

        fun build(context: Context): RoomDBProvider =
            Room.databaseBuilder(context, RoomDBProvider::class.java, NAME)
                .fallbackToDestructiveMigration(true)
                .build()
    }
}