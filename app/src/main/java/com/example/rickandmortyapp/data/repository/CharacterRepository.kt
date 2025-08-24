package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.domain.model.CharacterModel

interface CharacterRepository {
    suspend fun getCharacters(page: Int): CharacterModel
}
