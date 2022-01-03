package com.example.notboredapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notboredapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val participants = intent.extras!!.getString("participants")
        //binding.TVParticipantResult.text = participants
    }
}