package com.example.notboredapp

import android.content.Intent
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
        val extra =intent.extras
        val participants=extra?.getString("participants")

        binding.btnRandom.setOnClickListener(){

            val intent = Intent(this,RandomActivity::class.java)

            intent.putExtra("participants",participants)
            startActivity(intent)
            finish()


        }

    }

    private fun initiateRecyclerView() {
        adapter = SuggestAdapter(suggestionList, this)
        binding.rvItems.adapter = adapter
    }
}