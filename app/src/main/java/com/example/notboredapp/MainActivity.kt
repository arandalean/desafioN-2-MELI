package com.example.notboredapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notboredapp.databinding.ActivityMainBinding
import com.example.notboredapp.retrofit.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener { start() }
        binding.tvTerms.setOnClickListener { terms() }
    }

    private fun terms() {
        val intent = Intent(this, TermsActivity::class.java)
        startActivity(intent)
    }

    private fun start() {
        var participants = binding.etParticipants.text.toString()
        if (participants == "0") {
            Toast.makeText(this, "Number cannot be 0", Toast.LENGTH_SHORT).show()
        } else {
            if (participants.isNullOrBlank()) {
                participants = "0"
            }
            val intent = Intent(this, SuggestionActivity::class.java)
            intent.putExtra("participants", participants)
            startActivity(intent)
        }

    }
}