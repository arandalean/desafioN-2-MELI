package com.example.notboredapp

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

    var type : String? = ""
    var participants : Int? = 0
    var price : Float? = 0f
    var description : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/api/activity/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun searchRandom(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getSuggestionRandom()
            val suggestion = call.body()
            runOnUiThread{
                if (call.isSuccessful){
                    type = suggestion?.type
                    participants = suggestion?.participants ?: 0
                    price = suggestion?.price
                    description = suggestion?.activity
                    // Llamar a funcion con Random Activity
                    showRandom(type, participants!!,price, description)
                } else {
                    showError()
                }
            }


        }
    }

    private fun showRandom(type: String?, participants: Int, price: Float?, description: String?) {
        TODO("Not yet implemented")
    }

    private fun showError() {
        Toast.makeText(this, "An error has happened", Toast.LENGTH_SHORT).show()
    }
}