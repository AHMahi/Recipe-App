package com.example.myrecepiebook

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecipe(recipe: Recipe)

    @Query("SELECT * FROM  recipe_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Recipe>>

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)
}