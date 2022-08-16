package com.example.pokemonapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.network.Pokemon
import com.example.pokemonapp.network.PokemonApi
import com.example.pokemonapp.network.PokemonApiService
import kotlinx.coroutines.launch
import retrofit2.http.GET

enum class PokemonApiStatus {LOADING, ERROR, DONE}

class PokemonViewModel : ViewModel() {

    private val _status = MutableLiveData<PokemonApiStatus>()
    val status: LiveData<PokemonApiStatus> = _status

    private val _pokemons = MutableLiveData<List<Pokemon>>()
    val pokemons: LiveData<List<Pokemon>> = _pokemons

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon : LiveData<Pokemon> = _pokemon


    // TODO: Create properties to represent MutableLiveData and LiveData for a single amphibian object.
    //  This will be used to display the details of an amphibian when a list item is clicked


    // TODO: Create a function that gets a list of amphibians from the api service and sets the
    //  status via a Coroutine

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            _status.value = PokemonApiStatus.LOADING
            try {
                _pokemons.value = PokemonApi.retrofitService.getData()
                _status.value = PokemonApiStatus.DONE
            } catch (e: Exception) {
                _pokemons.value = listOf()
                _status.value = PokemonApiStatus.ERROR
            }
        }
    }


    fun onPokemonClicked(pokemon: Pokemon) {
        _pokemon.value = pokemon
    }
}