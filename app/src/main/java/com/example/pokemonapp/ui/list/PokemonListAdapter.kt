package com.example.pokemonapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.databinding.ListViewItemBinding
import com.example.pokemonapp.repositories.PokemonListItem


class PokemonListAdapter(private val clickListener: PokemonListener) :
    PagingDataAdapter<PokemonListItem, PokemonListAdapter.PokemonViewHolder>(DiffCallback) {

    class PokemonViewHolder(var binding: ListViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: PokemonListener, pokemon : PokemonListItem) {
            binding.pokemon = pokemon
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PokemonListItem>() {
        override fun areItemsTheSame(oldItem: PokemonListItem, newItem: PokemonListItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: PokemonListItem, newItem: PokemonListItem): Boolean {
            return oldItem.url == newItem.url
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder : PokemonViewHolder, position : Int) {
        val pokemon = getItem(position)
        holder.bind(clickListener, pokemon!!)
    }
}