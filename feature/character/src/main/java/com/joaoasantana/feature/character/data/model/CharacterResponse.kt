package com.joaoasantana.feature.character.data.model

import com.google.gson.annotations.SerializedName
import com.joaoasantana.core.common.constants.GENDER
import com.joaoasantana.core.common.constants.ID
import com.joaoasantana.core.common.constants.IMAGE
import com.joaoasantana.core.common.constants.NAME
import com.joaoasantana.core.common.constants.SPECIES
import com.joaoasantana.core.common.constants.STATUS
import com.joaoasantana.core.common.constants.TYPE

data class CharacterResponse(
    @SerializedName(ID) val id: Int,
    @SerializedName(NAME) val name: String,
    @SerializedName(STATUS) val status: String,
    @SerializedName(SPECIES) val species: String,
    @SerializedName(TYPE) val type: String,
    @SerializedName(GENDER) val gender: String,
    @SerializedName(IMAGE) val image: String,
)
