package com.joaoasantana.core.common.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavGraph {

    @Serializable
    data object CharacterGraph : NavGraph
}
