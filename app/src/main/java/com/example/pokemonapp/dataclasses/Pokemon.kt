package com.example.pokemonapp.dataclasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Pokemon (
    val name : String,
    val type : String,
    val description : String
)

