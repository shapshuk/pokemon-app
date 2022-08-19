package com.example.pokemonapp

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import com.example.pokemonapp.repositories.PokemonListItem
import com.example.pokemonapp.ui.list.PokemonApiStatus
import com.example.pokemonapp.ui.PokemonListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: PagingData<PokemonListItem>) {
//    val adapter = recyclerView.adapter as PokemonListAdapter
//
//    adapter.submitData(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: PokemonApiStatus?) {
    when(status) {
        PokemonApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        PokemonApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        PokemonApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}