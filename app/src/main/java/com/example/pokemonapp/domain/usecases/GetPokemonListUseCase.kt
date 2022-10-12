package com.example.pokemonapp.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.example.pokemonapp.repositories.PokemonListItem
import com.example.pokemonapp.repositories.PokemonRepository
import com.example.pokemonapp.repositories.SinglePokemon
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private val repository : PokemonRepository) {

    operator fun invoke(): LiveData<PagingData<PokemonListItem>> {
        return repository.getPokemonList().asLiveData()
    }

}