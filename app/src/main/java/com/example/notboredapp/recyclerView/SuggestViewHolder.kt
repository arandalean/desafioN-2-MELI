package com.example.notboredapp.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.notboredapp.databinding.SuggestionItemBinding

class SuggestViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = SuggestionItemBinding.bind(view)

    fun bind(suggestionAtPosition : String) {
        binding.tvSuggestion.text = suggestionAtPosition
    }
}