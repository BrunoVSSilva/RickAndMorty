package com.example.rickandmortyapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.repository.CharacterRepository
import com.example.rickandmortyapp.domain.model.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickAndMortyViewModel @Inject constructor(
    private val repo: CharacterRepository
) : ViewModel() {

    private val _characters = MutableStateFlow<CharacterModel?>(null)
    val characters: StateFlow<CharacterModel?> get() = _characters

    fun fetchCharacters(page: Int) {
        viewModelScope.launch {
            try {
                val result = repo.getCharacters(page)
                _characters.value = result
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}