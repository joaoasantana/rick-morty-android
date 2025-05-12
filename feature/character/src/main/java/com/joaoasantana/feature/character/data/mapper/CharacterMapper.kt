package com.joaoasantana.feature.character.data.mapper

import com.joaoasantana.feature.character.data.model.CharacterEntity
import com.joaoasantana.feature.character.data.model.CharacterResponse
import com.joaoasantana.feature.character.domain.entity.Character

fun CharacterEntity.toDomain(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        image = image,
    )
}

fun CharacterResponse.toEntity(page: Int): CharacterEntity {
    return CharacterEntity(
        id = id,
        page = page,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        image = image,
    )
}
