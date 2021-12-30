package com.example.notboredapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notboredapp.databinding.ActivityMainBinding

class SuggestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}