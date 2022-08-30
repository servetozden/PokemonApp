package com.example.pokemonapp.model

import com.google.gson.annotations.SerializedName

data class PokemonInfo (

    @SerializedName("base_experience")
    var base_experience : String,

    @SerializedName("height")
    var height : String,

    @SerializedName("weight")
    var weight : String,


        )

