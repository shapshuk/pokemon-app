package com.example.pokemonapp.ui.list


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokemonapp.repositories.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import kotlinx.coroutines.flow.Flow
import retrofit2.converter.moshi.MoshiConverterFactory

enum class PokemonApiStatus {LOADING, ERROR, DONE}

class ListViewModel : ViewModel() {

    private val repository : PokemonRepository =
        PokemonRepository(Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build())).build().create(PokemonApi::class.java))

    private val _status = MutableLiveData<PokemonApiStatus>()
    val status: LiveData<PokemonApiStatus> = _status

    lateinit var pokemons : Flow<PagingData<PokemonListItem>>

    init {
        getPokemonList()
    }

    private fun getPokemonList() {

        viewModelScope.launch {
            pokemons = repository.getPokemonList().cachedIn(viewModelScope)
        }

//        viewModelScope.launch {
//            _status.value = PokemonApiStatus.LOADING
//            try {
//                _pokemons.value = PokemonApi.retrofitService.getData()
//                _status.value = PokemonApiStatus.DONE
//            } catch (e: Exception) {
//                _pokemons.value = listOf()
//                _status.value = PokemonApiStatus.ERROR
//            }
//        }
    }
}