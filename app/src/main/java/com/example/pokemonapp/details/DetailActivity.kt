package com.example.pokemonapp.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemonapp.R
import com.example.pokemonapp.adapter.PokemonAdapter
import com.example.pokemonapp.databinding.ActivityDetailBinding
import com.example.pokemonapp.model.PokemonResultNetModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val viewModel: DetailsViewModel by  viewModels<DetailsViewModel>()

    lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)


        val extras = intent.extras
        var backgroundColor = extras?.getInt("backgroundColor")
        val name = intent.getStringExtra("name")
        viewModel.getListPokemon(name!!)

        val urlImage = intent.getStringExtra("imageUrl")
        observeViewModel()

        Glide.with(this).load(urlImage).into(binding.header)
        binding.name.text = name
        binding.header.setBackgroundColor(backgroundColor!!)


         window.statusBarColor = backgroundColor


        binding.arrow.setOnClickListener {
            onBackPressed()
        }


    }

    fun observeViewModel() {
        viewModel.pokemonInfoNetModel.observe(this, androidx.lifecycle.Observer { it ->
            it?.let {

                binding.height.text = it.height
                binding.weight.text = it.weight


            }
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}