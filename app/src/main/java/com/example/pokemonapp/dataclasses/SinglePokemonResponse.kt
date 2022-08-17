package com.example.pokemonapp.dataclasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SinglePokemonResponse(
    val name : String,
    val types : String,
    val weight : Int,
    val height : Int,
    val sprite : String  // front_default sprite
) : Parcelable