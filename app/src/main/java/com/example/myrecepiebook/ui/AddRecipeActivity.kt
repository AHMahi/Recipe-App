package com.example.myrecepiebook.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.myrecepiebook.Constants.RECIPE_DATABASE
import com.example.myrecepiebook.R
import com.example.myrecepiebook.db.Recipe
import com.example.myrecepiebook.db.RecipeDatabase
import com.example.myrecepiebook.databinding.ActivityAddRecipeBinding
import com.google.android.material.snackbar.Snackbar

class AddRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecipeBinding
    private val recipeDB : RecipeDatabase by lazy {
        Room.databaseBuilder(this, RecipeDatabase::class.java, RECIPE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    private lateinit var recipe: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            createRecipe.setOnClickListener{

                val recipeName = editRecipeName.text.toString()
                val recipeImage = R.drawable.avocado_toast
                val recipeDesc= editRecipeDescription.text.toString()
                val recipeInstructions = editRecipeInstructions.text.toString()

                if(recipeName.isNotEmpty() || recipeDesc.isNotEmpty() || recipeInstructions.isNotEmpty()){

                    recipe = Recipe(0, recipeImage, recipeName, recipeDesc, recipeInstructions)
                    recipeDB.doa().addRecipe(recipe)
                    finish()//go back to prev page automatically after task finish
                }
                else{
                    Snackbar.make(it,"Recipe Name, Description or Instruction cannot be empty", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }


}