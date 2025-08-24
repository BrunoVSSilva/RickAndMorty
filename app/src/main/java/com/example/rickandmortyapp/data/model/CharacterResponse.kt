package com.example.rickandmortyapp.data.model

import com.example.rickandmortyapp.domain.model.Character
import com.example.rickandmortyapp.domain.model.CharacterModel
import com.example.rickandmortyapp.domain.model.InfoCharacterModel
import com.google.gson.annotations.SerializedName

data class CharacterListResponse(
    @SerializedName("info") val info: CharacterInfoResponse?,
    @SerializedName("results") val results: List<CharacterResponse?>
)

data class CharacterInfoResponse(
    @SerializedName("count") val count: Int?,
    @SerializedName("pages") val pages: Int?,
    @SerializedName("next") val next: String?,
    @SerializedName("prev") val prev: String?
)

data class CharacterResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("species") val species: String?,
    @SerializedName("image") val image: String?
)

fun CharacterListResponse.toModel(): CharacterModel {
    return CharacterModel(
        info = InfoCharacterModel(
            count = this.info?.count ?: 0,
            pages = this.info?.pages ?: 0,
            next = this.info?.next,
            prev = this.info?.prev
        ),
        results = this.results.map { character ->
            Character(
                id = character?.id ?: 0,
                name = character?.name.orEmpty(),
                status = character?.status.orEmpty(),
                species = character?.species.orEmpty(),
                image = character?.image.orEmpty()
            )
        }
    )
}
