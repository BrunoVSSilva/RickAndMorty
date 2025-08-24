package com.example.rickandmortyapp.domain.model

data class CharacterModel(
    val info: InfoCharacterModel,
    val results: List<Character>
)

data class InfoCharacterModel(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String
)