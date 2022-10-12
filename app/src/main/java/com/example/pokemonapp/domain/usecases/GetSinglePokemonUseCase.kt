package com.example.pokemonapp.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.paging.PagingData
import com.example.pokemonapp.repositories.PokemonListItem
import com.example.pokemonapp.repositories.PokemonRepository
import com.example.pokemonapp.repositories.SinglePokemon
import javax.inject.Inject

class GetSinglePokemonUseCase @Inject constructor(private val repository : PokemonRepository) {

    operator fun invoke(pokemonName : String) : LiveData<SinglePokemon> {
        return liveData {
            emit(repository.getSinglePokemon(pokemonName))
        }
    }
}