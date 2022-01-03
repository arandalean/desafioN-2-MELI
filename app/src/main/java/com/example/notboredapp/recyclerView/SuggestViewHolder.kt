package com.example.notboredapp.recyclerView

import android.content.Context
import android.content.Intent

import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.notboredapp.DetailsActivity
import com.example.notboredapp.SuggestionActivity

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