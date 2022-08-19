package com.example.pokemonapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentPokemonDetailBinding
import com.example.pokemonapp.ui.list.ListViewModel

class PokemonDetailFragment : Fragment() {

    private val viewModel: DetailViewModel by activityViewModels()

    private val args: PokemonDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) : View? {
        val binding = FragmentPokemonDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel



        // TODO: change to navigation controller
        val toolbar = binding.toolbar
        toolbar.setNavigationIcon(R.drawable.ic_back_button)

        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onPokemonOpened(args.pokemonName)
    }
}