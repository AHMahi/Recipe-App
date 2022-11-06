package com.example.myrecepiebook

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


private var ARG_PARAM1 = "param1"
private var ARG_PARAM2 = "param2"



class ModalFragment : BottomSheetDialogFragment() {

    private var recipe: Recipe? = null

    companion object {
        @JvmStatic
        fun newInstance(recipe: Recipe) =
            ModalFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("RECIPES", recipe)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipe = it.getParcelable("RECIPES")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_recipe, container, false)

        val fragImgview= view.findViewById<ImageView>(R.id.Fragment_imageView)
        val fragHeadingTv = view.findViewById<TextView>(R.id.Fragment_heading_textView)
        val fragDescTv = view.findViewById<TextView>(R.id.Fragment_description_textView)
        val frag_button = view.findViewById<TextView>(R.id.frag_button)

        recipe?.let {
            fragImgview.setImageResource(it.recipeImage)
            fragHeadingTv.text = it.recipeName
            fragDescTv.text = it.recipeDesc

            frag_button.setOnClickListener {
                val intent = Intent(activity, favouriteRecipeActivity::class.java)
                intent.putExtra("recipes", recipe)
                startActivity(intent)
            }
        }
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       BottomSheetBehavior.STATE_EXPANDED
    }


}