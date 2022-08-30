package com.example.pokemonapp.details

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonapp.di.NetworkModule
import com.example.pokemonapp.model.PokemonInfo
import com.example.pokemonapp.model.PokemonNetModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.*

@ViewModelScoped
class DetailsViewModel : ViewModel(), LifecycleObserver {


    val pokemonService = NetworkModule
    var job: Job? = null
    val pokemonInfoNetModel = MutableLiveData<PokemonInfo>()
    val exchangeLoadError = MutableLiveData<String?>()



    fun getListPokemonDetail(customPokemon : String) {
        getListPokemon(customPokemon)
    }


    fun getListPokemon(name : String){

        job  = CoroutineScope(Dispatchers.IO ).launch {
            val response = pokemonService.pokemonInfo(name)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    pokemonInfoNetModel.value = response.body()
                }else{
                    onError("Error: ${response.message()}")

                }
            }
        }
    }

    private fun onError(message: String) {
        exchangeLoadError.value = message

    }
}