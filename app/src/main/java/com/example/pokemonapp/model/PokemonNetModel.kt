package com.example.pokemonapp.model

import com.google.gson.annotations.SerializedName

data class PokemonNetModel(


    @SerializedName("results")
    var results: List<PokemonResultNetModel>
)
