package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.api.RickAndMortyApi
import com.example.rickandmortyapp.data.model.toModel
import com.example.rickandmortyapp.domain.model.CharacterModel
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): CharacterModel {
        val response = api.getCharacters(page)
        return response.toModel()
    }
}