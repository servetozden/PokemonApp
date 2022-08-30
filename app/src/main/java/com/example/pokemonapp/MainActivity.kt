package com.example.pokemonapp

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.adapter.PokemonAdapter
import com.example.pokemonapp.databinding.ActivityMainBinding
import com.example.pokemonapp.model.PokemonResultNetModel
import com.example.pokemonapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by  viewModels<MainViewModel>()
    lateinit var binding: ActivityMainBinding

    private lateinit var adapter: PokemonAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // XML variable kısmında data göndermek istersek alt satırdaki kodda izin vermek gerekiyor . YOksa olmuyor .
        //binding.adapter = viewModel.pokemonNetModel.value


        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        // yukardaki lazy extension u bu alttaki koddan kurtarıyor.
      //  viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.getListPokemon()

        observeViewModel()

        //binding.mainToolbar.setBackgroundColor(R.color.md_yellow_200)

       // val window: Window = this.getWindow()

       // window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

       /* binding.mainToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.md_yellow_200));

        window.statusBarColor = ContextCompat.getColor(this, R.color.md_yellow_200)*/
    }

    fun observeViewModel() {
        viewModel.pokemonNetModel.observe(this, androidx.lifecycle.Observer { it ->
            it?.let {

                adapter = PokemonAdapter(it.results as ArrayList<PokemonResultNetModel>)

                val recyclerView: RecyclerView = binding.recyclerView
                recyclerView.adapter = adapter


            }
        })

    }




}
