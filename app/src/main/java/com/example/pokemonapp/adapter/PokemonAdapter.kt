package com.example.pokemonapp.adapter

import android.content.Intent
import android.graphics.Color
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemonapp.R
import com.example.pokemonapp.details.DetailActivity
import com.example.pokemonapp.model.PokemonNetModel
import com.example.pokemonapp.model.PokemonResultNetModel
import com.google.android.material.card.MaterialCardView
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList


class PokemonAdapter (private var dataCategoryDetailNetModel: ArrayList<PokemonResultNetModel>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonAdapter.ViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: PokemonAdapter.ViewHolder, position: Int) {

        var categoryDetail = dataCategoryDetailNetModel[position]

        val index = categoryDetail.url.split("/".toRegex()).dropLast(1).last()

       // holder.cardView.setCardBackgroundColor(R.color.black)
// databinding halne cevirip öyle izin vermek gerekiyor.
        val rnd = Random()
        val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.cardView.setCardBackgroundColor(color)

        val urlImage = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"

        holder.textViewMealCategoryDetailName.text = categoryDetail.name
        Glide.with(holder.itemView.context).load(urlImage).into(holder.imageViewUrl)


        holder.constraintAllItem.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)

            intent.putExtra("backgroundColor",color)
            intent.putExtra("imageUrl",urlImage)
            intent.putExtra("name",categoryDetail.name)


            //listener?.onClick(AlbumsData)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {

        return dataCategoryDetailNetModel.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val textViewMealCategoryDetailName: TextView
        val imageViewUrl: ImageView
        val cardView: MaterialCardView
        val constraintAllItem: ConstraintLayout





        init {
            textViewMealCategoryDetailName = mView.findViewById<View>(R.id.name) as TextView
            imageViewUrl = mView.findViewById<View>(R.id.image) as ImageView
            cardView = mView.findViewById<View>(R.id.cardView) as MaterialCardView

            constraintAllItem = mView.findViewById<View>(R.id.constraintAllItem) as ConstraintLayout







        }
    }
}