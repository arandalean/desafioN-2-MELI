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
    }

    private fun start() {
        val participants = binding.etParticipants.text.toString()
        val intent = Intent(this, SuggestionActivity::class.java)
        intent.putExtra("participants", participants)
        startActivity(intent)
        finish()
    }
}