package com.example.myrecepiebook.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Entity
import androidx.room.Room
import com.example.myrecepiebook.Constants.BUNDLE_RECIPE_ID
import com.example.myrecepiebook.Constants.RECIPE_DATABASE
import com.example.myrecepiebook.R
import com.example.myrecepiebook.databinding.UpdateRecipeBinding
import com.example.myrecepiebook.db.Recipe
import com.example.myrecepiebook.db.RecipeDatabase
import com.google.android.material.snackbar.Snackbar

class UpdateRecipeActivity : AppCompatActivity() {

    private lateinit var binding: UpdateRecipeBinding
    private val recipeDB: RecipeDatabase by lazy {
        Room.databaseBuilder(this, RecipeDatabase::class.java, RECIPE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    private lateinit var recipeEntity: Recipe
    private var recipeId = 0
    private var defaultRecipeImage = R.drawable.avocado_toast
    private var defaultRecipeName = ""
    private var defaultRecipeDesc = ""
    private var defaultRecipeInstruction = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UpdateRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //if extras active rn the block of code otherwise don't
        intent.extras?.let {
            recipeId = it.getInt(BUNDLE_RECIPE_ID)
        }

        binding.apply {
            defaultRecipeName = recipeDB.doa().getRecipe(recipeId).recipeName
            defaultRecipeImage = recipeDB.doa().getRecipe(recipeId).recipeImage
            defaultRecipeDesc = recipeDB.doa().getRecipe(recipeId).recipeDesc
            defaultRecipeInstruction = recipeDB.doa().getRecipe(recipeId).recipeInstructions

            editRecipeName.setText(defaultRecipeName)
            editRecipeDescription.setText(defaultRecipeDesc)
            editRecipeInstructions.setText(defaultRecipeInstruction)

            deleteRecipe.setOnClickListener{
                recipeEntity = Recipe(recipeId, defaultRecipeImage, defaultRecipeName, defaultRecipeDesc, defaultRecipeInstruction)
                recipeDB.doa().deleteRecipe(recipeEntity)
                finish()
            }

            updateRecipe.setOnClickListener{
                val recipeName = editRecipeName.text.toString()
                val recipeDesc = editRecipeDescription.text.toString()
                val recipeIns = editRecipeInstructions.text.toString()
                if(recipeName.isNotEmpty() || recipeDesc.isNotEmpty()){
                    recipeEntity =  Recipe(recipeId, recipeImage = R.drawable.avocado_toast, recipeName, recipeDesc, recipeIns)
                    recipeDB.doa().updateRecipe(recipeEntity)
                    finish()
                }
                else{
                    Snackbar.make(it,"Recipe Name, Description or Instruction cannot be empty", Snackbar.LENGTH_SHORT).show()
                }

            }
        }

    }
}