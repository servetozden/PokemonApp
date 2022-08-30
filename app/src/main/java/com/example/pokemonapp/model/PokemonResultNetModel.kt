package com.example.pokemonapp.model

import com.google.gson.annotations.SerializedName

data class PokemonResultNetModel(
    @SerializedName("name")
    var name : String,

    @SerializedName("url")
    var url : String
)
