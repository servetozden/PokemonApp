package com.example.pokemonapp.service

import com.example.pokemonapp.model.PokemonInfo
import com.example.pokemonapp.model.PokemonNetModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon/")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 90,
    ): Response<PokemonNetModel>

    @GET("pokemon/")
    suspend fun fetchPokemonListImage(
        @Query("limit") limit: Int = 20,
    ): Response<PokemonNetModel>


    @GET("pokemon/{name}")
    suspend fun fetchPokemonInfo(@Path("name") name: String): Response<PokemonInfo>
}