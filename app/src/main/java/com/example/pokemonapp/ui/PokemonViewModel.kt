package com.example.pokemonapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.dataclasses.Pokemon
import com.example.pokemonapp.dataclasses.PokemonResponse
import com.example.pokemonapp.network.PokemonApi
import kotlinx.coroutines.launch

enum class PokemonApiStatus {LOADING, ERROR, DONE}

class PokemonViewModel : ViewModel() {

    private val _status = MutableLiveData<PokemonApiStatus>()
    val status: LiveData<PokemonApiStatus> = _status

    private val _pokemons = MutableLiveData<List<PokemonResponse>>()
    val pokemons: LiveData<List<PokemonResponse>> = _pokemons

    private val _pokemon = MutableLiveData<PokemonResponse>()
    val pokemon : LiveData<PokemonResponse> = _pokemon

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            _status.value = PokemonApiStatus.LOADING
            try {
                _pokemons.value = PokemonApi.getPokemons()
                _status.value = PokemonApiStatus.DONE
            } catch (e: Exception) {
                _pokemons.value = listOf()
                _status.value = PokemonApiStatus.ERROR
            }
        }
    }

    fun onPokemonClicked(pokemon: PokemonResponse) {
        _pokemon.value = pokemon
    }
}