package com.example.pokemonapp.di

import android.content.ContentValues.TAG
import android.util.Log
import com.example.pokemonapp.model.PokemonInfo
import com.example.pokemonapp.model.PokemonNetModel
import com.example.pokemonapp.service.APIUrl
import com.example.pokemonapp.service.PokemonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    suspend fun provideRetrofit(): PokemonApi {
        return Retrofit.Builder()
            .baseUrl(APIUrl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }

    @Singleton
    @Provides
    suspend fun pokemonList(): Response<PokemonNetModel> {
        Log.d("pokemonAllList", "pokemonAllList" + provideRetrofit().fetchPokemonList().raw().request().url() )

        return provideRetrofit().fetchPokemonList()

    }

    @Singleton
    @Provides
    suspend fun pokemonImage(): Response<PokemonNetModel> {

        return provideRetrofit().fetchPokemonList()
    }

    @Singleton
    @Provides
    suspend fun pokemonInfo(name : String): Response<PokemonInfo> {
        Log.d("pokemonInfoURL", "pokemonInfoURL" +provideRetrofit().fetchPokemonInfo(name).raw().request().url() )

        return provideRetrofit().fetchPokemonInfo(name)


    }
}