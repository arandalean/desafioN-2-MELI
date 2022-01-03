package com.example.notboredapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notboredapp.databinding.ActivityMainBinding
import com.example.notboredapp.databinding.ActivityTermsBinding

class TermsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTermsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BTCloseTerms.setOnClickListener(){
            finish()
        }
    }
}