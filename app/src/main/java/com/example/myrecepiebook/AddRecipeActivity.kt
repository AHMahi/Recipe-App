package com.example.myrecepiebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.lifecycleScope
import com.example.myrecepiebook.databinding.ActivityAddRecipeBinding
import kotlinx.coroutines.launch
import java.util.*

class AddRecipeActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    private lateinit var binding: ActivityAddRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val recipe = intent.getParcelableExtra<Recipe>("recipes")

        val recipeDatabase = RecipeDatabase.getDatabase(this)

        binding.createRecipe.setOnClickListener {
            val name = binding.recipeName.text.toString()
            val description = binding.recipeDescription.text.toString()
            val instructions = binding.recipeInstructions.text.toString()
            val newRecipe = Recipe(id = UUID.randomUUID(), recipeName = name, recipeDesc = description, recipeInstructions = instructions)

            lifecycleScope.launch {
                recipeDatabase.recipeDao().addRecipe(newRecipe)
                 recipeDatabase.recipeDao().readAllData().observe(this@AddRecipeActivity) {
                     Log.d("AddRecipeActivity", it.toString())
                 }

            }
        }

    }



}