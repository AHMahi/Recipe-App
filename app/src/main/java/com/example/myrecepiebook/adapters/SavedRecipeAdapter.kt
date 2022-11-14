package com.example.myrecepiebook.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecepiebook.Constants.BUNDLE_RECIPE_ID
import com.example.myrecepiebook.databinding.SavedRecipeItemBinding
import com.example.myrecepiebook.db.Recipe
import com.example.myrecepiebook.ui.UpdateRecipeActivity


class SavedRecipeAdapter : RecyclerView.Adapter<SavedRecipeAdapter.ViewHolder>(){

    private lateinit var binding: SavedRecipeItemBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SavedRecipeAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = SavedRecipeItemBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: SavedRecipeAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetText")
        fun bind(item: Recipe) {
            binding.apply {
                savedRecipeName.text = item.recipeName
                savedRecipeDescription.text = item.recipeDesc
                savedRecipeInstruction.text = item.recipeInstructions

                root.setOnClickListener{
                    val intent = Intent(context, UpdateRecipeActivity::class.java)
                    intent.putExtra(BUNDLE_RECIPE_ID, item.recipeId)
                    context.startActivity(intent)
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Recipe>(){
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.recipeId == newItem.recipeId
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

}
