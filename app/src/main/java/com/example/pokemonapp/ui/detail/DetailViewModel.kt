package com.example.pokemonapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.domain.usecases.GetSinglePokemonUseCase
import com.example.pokemonapp.repositories.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getSinglePokemonUseCase: GetSinglePokemonUseCase): ViewModel() {

    lateinit var pokemon : LiveData<SinglePokemon>

    fun onPokemonOpened(pokemonName : String){
        viewModelScope.launch {
            pokemon = getSinglePokemonUseCase(pokemonName)
        }
    }
}