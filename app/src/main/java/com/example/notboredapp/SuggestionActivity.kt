package com.example.notboredapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notboredapp.databinding.ActivitySuggestionBinding
import com.example.notboredapp.recyclerView.SuggestAdapter

class SuggestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuggestionBinding
    private lateinit var adapter: SuggestAdapter
    private var suggestionList = mutableListOf("education", "recreational", "social", "diy", "charity", "cooking", "relaxation", "music", "busywork")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initiateRecyclerView()

    }

    private fun initiateRecyclerView() {
        adapter = SuggestAdapter(suggestionList)
        binding.rvItems.adapter = adapter
    }
}