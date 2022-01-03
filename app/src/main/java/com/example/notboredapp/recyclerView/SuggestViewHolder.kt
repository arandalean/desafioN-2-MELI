package com.example.notboredapp.recyclerView

<<<<<<< HEAD
import android.content.Context
import android.content.Intent
import android.os.Bundle
=======
import android.content.Intent
>>>>>>> 153c021 (Resolving details activity)
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.notboredapp.DetailsActivity
<<<<<<< HEAD
import com.example.notboredapp.SuggestionActivity
=======
>>>>>>> 153c021 (Resolving details activity)
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