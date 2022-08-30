package com.example.pokemonapp.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object RecyclerViewBinding  {


    @BindingAdapter("pokemonAdapter")
    fun pokemonListAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>){
        view.adapter = adapter.apply {

        }
    }

}