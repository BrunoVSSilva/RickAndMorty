package com.example.rickandmortyapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.FragmentCharacterListHomeBinding
import com.example.rickandmortyapp.ui.RickAndMortyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class CharacterListHome : Fragment(R.layout.fragment_character_list_home) {

    private var _binding: FragmentCharacterListHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RickAndMortyViewModel by activityViewModels()
    private lateinit var characterAdapter: CharacterListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterAdapter = CharacterListAdapter()

        binding.characterRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.characters.collect { characterModel ->
                characterModel?.results?.let { characters ->
                    characterAdapter.submitList(characters)
                }
            }
        }

        viewModel.fetchCharacters(1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
