package com.example.pokemonapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentPokemonListBinding

class PokemonListFragment : Fragment() {

    private val viewModel: PokemonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPokemonListBinding.inflate(inflater)
        // TODO: call the view model method that calls the amphibians api
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = PokemonListAdapter(PokemonListener { pokemon ->
            viewModel.onPokemonClicked(pokemon)
            findNavController()
                .navigate(R.id.action_pokemonListFragment_to_pokemonDetailFragment)
        })

        // Inflate the layout for this fragment
        return binding.root
    }

}