package com.example.pokemonapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.example.pokemonapp.repositories.PokemonListItem
import com.example.pokemonapp.repositories.SinglePokemon
import com.example.pokemonapp.ui.list.ListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PokemonDetailFragment : Fragment() {

    private val viewModel: DetailViewModel by activityViewModels()
    private val args: PokemonDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) : View? {
        val binding = FragmentPokemonDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val toolbar = binding.toolbar
        toolbar.setNavigationIcon(R.drawable.ic_back_button)

        toolbar.setNavigationOnClickListener {
            binding.root.findNavController().navigateUp()
        }

        viewModel.onPokemonOpened(args.pokemonName)

        viewModel.pokemon.observe( this.viewLifecycleOwner, Observer { pokemon ->
            // picture download
            binding.apply {
                Glide.with(root)
                    .load(pokemon.sprites?.front_default)
                    .into(binding.image)
            }

            // setting up view elements
            binding.name.text = pokemon.name?.capitalize()
            binding.height.text = "Height: ${pokemon.height?.div(0.1)?.toInt().toString()} cm"
            binding.weight.text = "Weight: ${pokemon.weight.toString()} kg"
            binding.types.text = pokemon.types?.joinToString(
                prefix = "Type: ",
                separator = ", ",
                transform = { it.type?.name.toString() }
            )
        })



        //inflate the layout for this fragment
        return binding.root
    }
}