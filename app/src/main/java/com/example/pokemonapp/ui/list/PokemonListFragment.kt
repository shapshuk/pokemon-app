package com.example.pokemonapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.pokemonapp.databinding.FragmentPokemonListBinding
import kotlinx.coroutines.launch

class PokemonListFragment : Fragment() {

    private val viewModel: ListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPokemonListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = PokemonListAdapter(PokemonListener { pokemon ->
            val direction = PokemonListFragmentDirections
                .actionPokemonListFragmentToPokemonDetailFragment(pokemon.name)
            findNavController().navigate(direction)
        })

        lifecycleScope.launch {
            val adapter = binding.recyclerView.adapter as PokemonListAdapter
            viewModel.pokemons.collect {
                adapter.submitData(it)
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}
