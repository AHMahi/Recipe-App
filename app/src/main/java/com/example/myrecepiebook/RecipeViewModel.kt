package com.example.myrecepiebook
import android.app.Application
import androidx.lifecycle.AndroidViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RecipeViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Recipe>>
    private val repository: RecipeRepository

    init {
        val RecipeDao = RecipeDatabase.getDatabase(application).recipeDao()
        repository = RecipeRepository(RecipeDao)
        readAllData = repository.readAllData
    }

    fun addRecipe(recipe: Recipe){
        viewModelScope.launch (Dispatchers.IO)  {
            repository.addRecipe(recipe)
        }
    }
}