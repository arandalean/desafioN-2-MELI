package com.example.notboredapp.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notboredapp.R

class SuggestAdapter(private var suggestionsList : List<String>, private val context : Context, private val participants: String) : RecyclerView.Adapter<SuggestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuggestViewHolder(layoutInflater.inflate(R.layout.suggestion_item, parent, false))
    }

    override fun onBindViewHolder(holder: SuggestViewHolder, position: Int) {

        val suggestionAtPosition = suggestionsList[position]
        holder.bind(suggestionAtPosition, context, participants)
    }

    override fun getItemCount(): Int = suggestionsList.size
}