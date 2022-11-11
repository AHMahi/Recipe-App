package com.example.myrecepiebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecepiebook.adapters.RecipeAdapter
import com.example.myrecepiebook.db.Recipe
import com.example.myrecepiebook.ui.AddRecipeActivity
import com.example.myrecepiebook.ui.SavedRecipeActivity
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    //add comments on the functions
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeList: ArrayList<Recipe>
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        mainImageClick()
        addRecipe()
        savedRecipe()
        init()
    }

    private fun init(){
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recipeList = ArrayList()

        addDataToList()
        recipeAdapter = RecipeAdapter(recipeList) {showDetail(it)}
        recyclerView.adapter = recipeAdapter
        recyclerView.layoutManager =  LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

    }

    private fun showDetail(item: Recipe) {

        val countryFragment = ModalFragment.newInstance(item)
        countryFragment.show(supportFragmentManager, "Recipes")
    }

    private fun addDataToList() {
        //each recipe in recipe List has an image and a text
        recipeList.add(Recipe(0, R.drawable.avocado_toast, "Avocado Spanish Toast", "Avocado toast is an open-face sandwich made with toasted bread topped with smashed (or sliced) avocado and (typically) fresh lemon juice, olive oil, and simple seasonings like salt and black pepper.","nothing"))
        recipeList.add(Recipe(1, R.drawable.biriyani, "Biryani", "Biryani is one of the most popular dishes in South Asia, as well as among the diaspora from the region. Similar dishes are also prepared in other parts of the world such as in Iraq, Thailand, and Malaysia. Biryani is the single most-ordered dish on Indian online food ordering and delivery services.", "nothing"))
        recipeList.add(Recipe(2, R.drawable.boba_tea, "Boba Tea", "Whatever you call it, in its most basic form, the drink consists of black tea, milk, ice, and chewy tapioca pearls, all shaken together like a martini and served with that famously fat straw to accommodate the marbles of tapioca that cluster at the bottom of the cup.", "nothing"))
        recipeList.add(Recipe(3, R.drawable.chichken_wrap, "Chicken Wrap", "Chicken Wrap", "nothing"))
        recipeList.add(Recipe(4, R.drawable.chicken_curry, "Chicken Curry", "Chicken Curry", "nothing"))
        recipeList.add(Recipe(5, R.drawable.fried_rice, "Fried Rice", "Fried Rice", "nothing"))
        recipeList.add(Recipe(6, R.drawable.garlic_shrimp, "Garlic Shrimp", "Garlic Shrimp", "nothing"))
        recipeList.add(Recipe(7, R.drawable.pho, "Pho", "Pho", "nothing"))
        recipeList.add(Recipe(8, R.drawable.roast_beef, "Roast Beef", "Roast Beef", "nothing"))
        recipeList.add(Recipe(9, R.drawable.sushi, "Sushi", "Sushi is traditionally made with medium-grain white rice, though it can be prepared with brown rice or short-grain rice. It is very often prepared with seafood, such as squid, eel, yellowtail, salmon, tuna or imitation crab meat. Many types of sushi are vegetarian.", "nothing"))
    }

    private fun mainImageClick() {
        var clickImage = findViewById<ImageView>(R.id.popular_imageView2)

        clickImage.setOnClickListener{
            val intent = Intent(this,favouriteRecipeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addRecipe() {
        var clickAdd = findViewById<Button>(R.id.add_recipe)

        clickAdd.setOnClickListener{
            val intent = Intent(this, AddRecipeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun savedRecipe() {
        var clickAdd = findViewById<Button>(R.id.show_recipe)

        clickAdd.setOnClickListener{
            val intent = Intent(this, SavedRecipeActivity::class.java)
            startActivity(intent)
        }
    }
}