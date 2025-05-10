package com.joaoasantana.feature.character.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joaoasantana.feature.character.data.constants.GENDER
import com.joaoasantana.feature.character.data.constants.IMAGE
import com.joaoasantana.feature.character.data.constants.NAME
import com.joaoasantana.feature.character.data.constants.SPECIES
import com.joaoasantana.feature.character.data.constants.STATUS
import com.joaoasantana.feature.character.data.constants.TYPE

private const val TABLE_NAME = "character"

@Entity(tableName = TABLE_NAME)
data class CharacterEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(NAME) val name: String,
    @ColumnInfo(STATUS) val status: String,
    @ColumnInfo(SPECIES) val species: String,
    @ColumnInfo(TYPE) val type: String,
    @ColumnInfo(GENDER) val gender: String,
    @ColumnInfo(IMAGE) val image: String,
)
