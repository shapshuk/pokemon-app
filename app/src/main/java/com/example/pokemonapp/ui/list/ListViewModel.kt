package com.example.pokemonapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokemonapp.repositories.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel( ) {
    lateinit var pokemons : LiveData<PagingData<PokemonListItem>>

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            pokemons = repository.getPokemonList().cachedIn(viewModelScope).asLiveData()
        }
    }
}