package com.example.pokemonapp.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokemonapp.dataclasses.PokemonResult
import com.example.pokemonapp.network.PokemonApi
import java.io.IOException

const val STARTING_OFFSET_INDEX = 0

class PokemonDataSource(private val pokemonApi: PokemonApi) :
    PagingSource<Int, PokemonResult>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResult> {
        val offset = params.key ?: STARTING_OFFSET_INDEX

        /*since pokeapi doesn't provide the search filter endpoint, here I am searching all of the 1118 pokemon based on the string user
        entered, the process will be repeated until all the 1118 pokemon in the api is searched, not the optimum solution, would be better if
        pokeapi had search endpoint.
        */

        /*if user doesn't pass search string the load size per scroll will  be 10 but if they provide it will be 100 as not everything will be displayed
        only that matches the search string
         */
        val loadSize = params.loadSize
        return try {
            val data = pokemonApi.getPokemons(loadSize, offset)

            //if search string isn't null filter the pokemon based on what user searched.
            val filteredData = data.results


            //next offset = last offset + loadsize, returning null is telling the paging 3 that there is no more to load and should stop
            LoadResult.Page(
                data = filteredData,
                prevKey = if (offset == STARTING_OFFSET_INDEX) null else offset - loadSize,
                nextKey = if (data.next == null) null else offset + loadSize
            )
        } catch (t: Throwable) {
            var exception = t

            if (t is IOException) {
                exception = IOException("Please check internet connection")
            }
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonResult>): Int? {
        return state.anchorPosition
    }


}