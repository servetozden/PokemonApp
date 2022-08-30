package com.example.pokemonapp.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonapp.di.NetworkModule
import com.example.pokemonapp.model.PokemonNetModel
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.internal.util.HalfSerializer.onError
import kotlinx.coroutines.*

@ViewModelScoped
class MainViewModel : ViewModel(), LifecycleObserver {

    val pokemonService = NetworkModule
    var job: Job? = null

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }
    val pokemonNetModel = MutableLiveData<PokemonNetModel>()
    val exchangeLoadError = MutableLiveData<String?>()

    init {
        getListPokemon()
    }

    fun getListPokemon(){
        job  = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
         val response = pokemonService.pokemonList()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    pokemonNetModel.value = response.body()
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