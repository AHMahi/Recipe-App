package com.example.myrecepiebook.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//This class contains the database holder and serves as the main access point for the underlying connection to the app's relational data

@Database(entities = [Recipe::class], version = 1)//whenever you add a new column or change some database feature, change the version here.
abstract class RecipeDatabase: RoomDatabase() {

    abstract fun doa(): RecipeDao

    /*companion object{//everything inside companion object will be visible to other classes
        @Volatile//initially instance is null
        private var INSTANCE: RecipeDatabase? = null//writes made to this field is immediately made visible to other threads

        //check if instance exists or not if it does then return it otherwise we make a new one
        fun getDatabase(context: Context): RecipeDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){// we make an instance of our room database
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "recipe_database"
                    )
                    .build()
                    INSTANCE = instance
                    return instance
            }

        }


    }*/
}