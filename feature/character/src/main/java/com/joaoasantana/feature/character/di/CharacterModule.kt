package com.joaoasantana.feature.character.di

import com.joaoasantana.feature.character.data.repository.CharacterRepositoryImpl
import com.joaoasantana.feature.character.domain.repository.CharacterRepository
import com.joaoasantana.feature.character.domain.usecase.RetrieveCharacterList
import com.joaoasantana.feature.character.domain.usecase.RetrieveCharacterListImpl
import com.joaoasantana.feature.character.presentation.ui.list.CharacterListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val characterModule = module {
    single { Dispatchers.IO }

    singleOf(::CharacterRepositoryImpl) { bind<CharacterRepository>() }
    singleOf(::RetrieveCharacterListImpl) { bind<RetrieveCharacterList>() }

    viewModelOf(::CharacterListViewModel)
}
