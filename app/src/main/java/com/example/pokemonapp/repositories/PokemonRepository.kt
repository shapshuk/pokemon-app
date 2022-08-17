package com.example.pokemonapp.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pokemonapp.datasource.PokemonDataSource
import com.example.pokemonapp.network.PokemonApi


class PokemonRepository(private val pokemonApi: PokemonApi) : BaseRepository() {

    //Returning the fetched data as flow

    fun getPokemon(searchString: String?) = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 25),
        pagingSourceFactory = {
            PokemonDataSource(pokemonApi)
        }
    ).flow

    suspend fun getSinglePokemon(id: Int) = safeApiCall {
        pokemonApi.getSinglePokemon(id)

    }


}