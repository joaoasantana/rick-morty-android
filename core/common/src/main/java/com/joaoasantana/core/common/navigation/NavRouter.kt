package com.joaoasantana.core.common.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRouter {

    @Serializable
    data object CharacterList : NavRouter
}
