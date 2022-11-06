package com.example.myrecepiebook

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*


@Entity(tableName = "recipe_table")
@Parcelize
data class Recipe(
    @PrimaryKey val id: UUID,
    @DrawableRes val recipeImage: Int = R.drawable.avocado_toast,
    @ColumnInfo(name = "recipe_name") val recipeName: String,
    @ColumnInfo(name = "recipe_desc") val recipeDesc: String,
    @ColumnInfo(name = "recipe_instructions") val recipeInstructions: String = ""
) : Parcelable
