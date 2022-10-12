package com.example.pokemonapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.repositories.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: PokemonRepository): ViewModel() {

    private val _pokemon = MutableLiveData<SinglePokemon>()
    val pokemon : LiveData<SinglePokemon> = _pokemon

    fun onPokemonOpened(pokemonName : String){
        viewModelScope.launch {
            _pokemon.value = repository.getSinglePokemon(pokemonName)
        }
    }
}