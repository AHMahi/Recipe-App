package com.example.myrecepiebook.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.myrecepiebook.Constants.RECIPE_DATABASE
import com.example.myrecepiebook.R
import com.example.myrecepiebook.adapters.RecipeAdapter
import com.example.myrecepiebook.adapters.SavedRecipeAdapter
import com.example.myrecepiebook.databinding.ActivitySavedRecipeBinding
import com.example.myrecepiebook.db.RecipeDatabase

class SavedRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySavedRecipeBinding
    private val recipeDB : RecipeDatabase by lazy {
        Room.databaseBuilder(this,RecipeDatabase::class.java,RECIPE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    private val savedRecipeAdapter by lazy {  SavedRecipeAdapter()  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =  ActivitySavedRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.floatingActionButton.setOnClickListener{
            startActivity(Intent(this,AddRecipeActivity::class.java))
        }
    }
    // to bring live data AKA refreshing on each addition
    override fun onResume() {
        super.onResume()
        checkSavedItem()
    }

    private fun checkSavedItem(){
        binding.apply {
            if(recipeDB.doa().readAllData().isNotEmpty()){
                recyclerViewSavedRecipe.visibility= View.VISIBLE
                tvNoSavedRecipe.visibility = View.GONE
                savedRecipeAdapter.differ.submitList(recipeDB.doa().readAllData())
                setupRecyclerView()
            }

            else{
                recyclerViewSavedRecipe.visibility = View.GONE
                tvNoSavedRecipe.visibility = View.VISIBLE
            }
        }
    }

    private fun setupRecyclerView(){
        binding.recyclerViewSavedRecipe.apply {
            layoutManager = LinearLayoutManager(this@SavedRecipeActivity)
            adapter = savedRecipeAdapter
        }
    }
}
