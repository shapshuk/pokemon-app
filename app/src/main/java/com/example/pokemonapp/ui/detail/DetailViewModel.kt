package com.example.pokemonapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.pokemonapp.repositories.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

enum class PokemonApiStatus {LOADING, ERROR, DONE}

class DetailViewModel : ViewModel() {

    private val repository : PokemonRepository =
        PokemonRepository(
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
                MoshiConverterFactory.create(
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()))
                .build().create(PokemonApi::class.java))


    private val _status = MutableLiveData<PokemonApiStatus>()
    val status: LiveData<PokemonApiStatus> = _status

    private val _pokemon = MutableLiveData<SinglePokemon>()
    val pokemon : LiveData<SinglePokemon> = _pokemon

    fun onPokemonOpened(pokemonName : String) : SinglePokemon?{
        viewModelScope.launch {
            _pokemon.value = repository.getSinglePokemon(pokemonName)
        }
        return _pokemon.value
    }
}