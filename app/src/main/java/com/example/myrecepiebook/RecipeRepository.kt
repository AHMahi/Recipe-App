package com.example.myrecepiebook

import androidx.lifecycle.LiveData

class RecipeRepository(private val recipeDao: RecipeDao) {
    val readAllData : LiveData<List<Recipe>> = recipeDao.readAllData()

    suspend fun addRecipe(recipe: Recipe){
        recipeDao.addRecipe(recipe)
    }


}