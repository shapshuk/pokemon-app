package com.example.pokemonapp.di

import com.example.pokemonapp.repositories.BASE_URL
import com.example.pokemonapp.repositories.PokemonApi
import com.example.pokemonapp.repositories.PokemonRepository
import com.example.pokemonapp.repositories.PokemonRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Component(modules = [AppModule::class])
interface AppComponent {
    fun pokemonRepository() : PokemonRepository
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun jsonAdapter() : Converter.Factory {
        return MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build())
    }

    @Provides
    fun provideRepository(jsonAdapter: Converter.Factory) : PokemonRepository {
        return PokemonRepositoryImpl(
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(jsonAdapter)
                .build()
                .create(PokemonApi::class.java))
    }
}