package com.example.pokemonapp.ui.list

import com.example.pokemonapp.repositories.PokemonListItem

class PokemonListener(val clickListener: (pokemon : PokemonListItem) -> Unit) {
    fun onClick(pokemon : PokemonListItem) = clickListener(pokemon)
}