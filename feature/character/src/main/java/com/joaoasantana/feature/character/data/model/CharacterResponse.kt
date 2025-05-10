package com.joaoasantana.feature.character.data.model

import com.google.gson.annotations.SerializedName
import com.joaoasantana.feature.character.data.constants.GENDER
import com.joaoasantana.feature.character.data.constants.ID
import com.joaoasantana.feature.character.data.constants.IMAGE
import com.joaoasantana.feature.character.data.constants.NAME
import com.joaoasantana.feature.character.data.constants.SPECIES
import com.joaoasantana.feature.character.data.constants.STATUS
import com.joaoasantana.feature.character.data.constants.TYPE

data class CharacterResponse(
    @SerializedName(ID) val id: Int,
    @SerializedName(NAME) val name: String,
    @SerializedName(STATUS) val status: String,
    @SerializedName(SPECIES) val species: String,
    @SerializedName(TYPE) val type: String,
    @SerializedName(GENDER) val gender: String,
    @SerializedName(IMAGE) val image: String,
)
