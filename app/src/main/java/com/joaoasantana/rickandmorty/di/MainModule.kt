package com.joaoasantana.rickandmorty.di

import com.joaoasantana.feature.character.data.source.CharacterService
import com.joaoasantana.feature.character.data.source.CharacterStorage
import com.joaoasantana.feature.character.di.characterModule
import com.joaoasantana.rickandmorty.infra.RetrofitProvider
import com.joaoasantana.rickandmorty.infra.RoomDBProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit

val infraModule = module {
    single<Retrofit> { RetrofitProvider().build() }
    single<RoomDBProvider> { RoomDBProvider.build(androidApplication()) }

    single<CharacterService> { get<Retrofit>().create(CharacterService::class.java) }
    single<CharacterStorage> { get<RoomDBProvider>().characterDao() }
}

val mainModule = listOf(
    infraModule,
    characterModule
)
