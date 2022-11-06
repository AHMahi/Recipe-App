package com.example.myrecepiebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(private val recipeList : List<Recipe>, private val listener: (Recipe) -> Unit) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.each_recipe, parent, false)
        return RecipeViewHolder(itemView)//each item instantiated inside the each_recipe layout
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]//get current item and
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val recipeImageView : ImageView = itemView.findViewById(R.id.recipe_imageView)
        val recipeNameTv : TextView = itemView.findViewById(R.id.recipeName_tv)

        fun bind(item: Recipe){
            recipeImageView.setImageResource(item.recipeImage)
            recipeNameTv.text = item.recipeName

            itemView.setOnClickListener{listener(item)}
        }
    }
}

