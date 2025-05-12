package com.joaoasantana.feature.character.presentation.mapper

import com.joaoasantana.feature.character.domain.entity.Character
import com.joaoasantana.feature.character.presentation.model.CharacterModel

fun Character.toModel(): CharacterModel {
    return CharacterModel(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        image = image,
    )
}