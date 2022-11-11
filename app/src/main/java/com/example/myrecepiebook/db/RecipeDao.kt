package com.example.myrecepiebook.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myrecepiebook.Constants.RECIPE_TABLE
import com.example.myrecepiebook.db.Recipe

@Dao
interface RecipeDao {

    //will have all the queries
    @Query("SELECT * FROM  $RECIPE_TABLE ORDER BY recipeImage ASC")
    fun readAllData(): MutableList<Recipe>

    @Query("SELECT * FROM  $RECIPE_TABLE WHERE recipeId LIKE :id")
    fun getRecipe(id : Int): Recipe

    @Insert(onConflict = OnConflictStrategy.REPLACE)//if there is a new user we will just ignore that
    fun addRecipe(recipe: Recipe)

    @Update
    fun updateRecipe(recipe: Recipe)

    @Delete
    fun deleteRecipe(recipe: Recipe)
}