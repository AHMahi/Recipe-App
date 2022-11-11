package com.example.myrecepiebook.db

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myrecepiebook.Constants.RECIPE_TABLE
import com.example.myrecepiebook.R
import kotlinx.parcelize.Parcelize
import java.util.*


@Entity(tableName = RECIPE_TABLE)
@Parcelize
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val recipeId: Int,
    @DrawableRes val recipeImage: Int = R.drawable.avocado_toast,
    @ColumnInfo(name = "recipe_name")
    val recipeName: String,
    @ColumnInfo(name = "recipe_desc")
    val recipeDesc: String,
    @ColumnInfo(name = "recipe_instructions")
    val recipeInstructions: String
) : Parcelable
