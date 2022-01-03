package com.example.notboredapp.recyclerView

import android.content.Context
import android.content.Intent
<<<<<<< HEAD
=======

>>>>>>> 9e9b98a017c4af97238bf54fcc5fd4e1b7391abe
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.notboredapp.DetailsActivity
<<<<<<< HEAD
=======
import com.example.notboredapp.SuggestionActivity

>>>>>>> 9e9b98a017c4af97238bf54fcc5fd4e1b7391abe
import com.example.notboredapp.databinding.SuggestionItemBinding

class SuggestViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = SuggestionItemBinding.bind(view)

    fun bind(suggestionAtPosition : String, context: Context) {
        binding.tvSuggestion.text = suggestionAtPosition
        binding.root.setOnClickListener { intent(context, suggestionAtPosition) }
    }

    fun intent(context : Context, suggestionAtPosition: String) {
        binding.root.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("suggestion", suggestionAtPosition)
            context.startActivity(intent)
        }
    }


}