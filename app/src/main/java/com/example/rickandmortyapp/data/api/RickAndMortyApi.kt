package com.example.rickandmortyapp.data.api

import com.example.rickandmortyapp.data.model.CharacterListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterListResponse
}